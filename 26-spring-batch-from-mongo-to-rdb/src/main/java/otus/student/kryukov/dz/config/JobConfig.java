package otus.student.kryukov.dz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import otus.student.kryukov.dz.domain.*;
import otus.student.kryukov.dz.repository.BookRepositoryRdb;
import otus.student.kryukov.dz.service.*;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String MIGRATION_DB_JOB_NAME = "migrationDbJob";

    private final BookRepositoryRdb bookRepositoryRdb;
    private final AuthorSingleService authorSingleService;
    private final GenreSingleService genreSingleService;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job migrationDbJob(Step stepBook, Step stepAuthorSingle, Step stepGenreSingle) {
        return jobBuilderFactory.get(MIGRATION_DB_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(stepBook)
                .next(stepAuthorSingle)
                .next(stepGenreSingle)
                .end()
                .build();
    }

    @Bean
    public Step stepBook(RepositoryItemWriter<BookRdb> writer,
                         MongoItemReader<BookMongo> reader,
                         ItemProcessor<BookMongo, BookRdb> itemProcessor)
    {
        return stepBuilderFactory.get("stepBook")
                .<BookMongo, BookRdb>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public MongoItemReader<BookMongo> reader(MongoTemplate template) {
        return new MongoItemReaderBuilder<BookMongo>()
                .name("mongoItemReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(BookMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<BookMongo, BookRdb> processor(BookConverterService bookConverterService) {
        return bookConverterService::mongoToRdbConvert;
    }

    @Bean
    public RepositoryItemWriter<BookRdb> writer() {
        return new RepositoryItemWriterBuilder<BookRdb>()
                .methodName("save")
                .repository(bookRepositoryRdb)
                .build();
    }

    @Bean
    public Step stepAuthorSingle() {
        return this.stepBuilderFactory.get("stepAuthorSingle")
                .tasklet(authorSingleTasklet())
                .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter authorSingleTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(authorSingleService);
        adapter.setTargetMethod("save");
        return adapter;
    }

    @Bean
    public Step stepGenreSingle() {
        return this.stepBuilderFactory.get("stepGenreSingle")
                .tasklet(genreSingleTasklet())
                .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter genreSingleTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(genreSingleService);
        adapter.setTargetMethod("save");
        return adapter;
    }
}