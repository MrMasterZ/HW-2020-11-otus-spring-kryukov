package otus.student.kryukov.dz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.student.kryukov.dz.repository.AuthorRepositoryRdb;
import otus.student.kryukov.dz.repository.BookRepositoryRdb;
import otus.student.kryukov.dz.repository.GenreRepositoryRdb;

import static otus.student.kryukov.dz.config.JobConfig.*;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final JobOperator jobOperator;
    private final BookRepositoryRdb bookRepositoryRdb;
    private final AuthorRepositoryRdb authorRepositoryRdb;
    private final GenreRepositoryRdb genreRepositoryRdb;

    @ShellMethod(value = "startMigrationJobMongoToRdb", key = "sm-mr")
    public void startMigrationJobMongoToRdb() throws Exception {
        jobOperator.startNextInstance(MIGRATION_DB_JOB_NAME);
    }

    // methods for easy check
    @ShellMethod(value = "get all book-objects", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        System.out.println(bookRepositoryRdb.findAll());
    }

    @ShellMethod(value = "get all author-objects", key = {"gaa", "getAllAuthors"})
    public void getAllAuthors() {
        System.out.println(authorRepositoryRdb.findAll());
    }

    @ShellMethod(value = "get all genre-objects", key = {"gag", "getAllGenres"})
    public void getAllGenres() {
        System.out.println(genreRepositoryRdb.findAll());
    }
}