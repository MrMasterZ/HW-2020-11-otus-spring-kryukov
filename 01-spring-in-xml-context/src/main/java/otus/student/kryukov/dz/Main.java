package otus.student.kryukov.dz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.student.kryukov.dz.dao.CSVStrategyReader;
import otus.student.kryukov.dz.dao.QuestionWriter;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            CSVStrategyReader reader = context.getBean(CSVStrategyReader.class);
            QuestionWriter writer = context.getBean(QuestionWriter.class);
            writer.write(reader.read());
        }
    }
}