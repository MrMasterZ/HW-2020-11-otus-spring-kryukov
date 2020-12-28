package otus.student.kryukov.dz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import otus.student.kryukov.dz.auth.AuthService;
import otus.student.kryukov.dz.check.Checker;
import otus.student.kryukov.dz.dao.CSVStrategyReader;
import otus.student.kryukov.dz.dao.QuestionWriter;
import otus.student.kryukov.dz.domain.Question;
import otus.student.kryukov.dz.domain.Student;
import otus.student.kryukov.dz.io.IOService;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {

    private static String passScore;
    private static IOService ioService;
    private static AuthService authService;
    private static CSVStrategyReader reader;
    private static QuestionWriter writer;
    private static Checker checker;

    @Autowired
    public Main(@Value("${pass.score}") String passScore, IOService ioService, AuthService authService, CSVStrategyReader reader, QuestionWriter writer, Checker checker) {
        this.ioService = ioService;
        this.authService = authService;
        this.reader = reader;
        this.writer = writer;
        this.checker = checker;
        this.passScore = passScore;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        poll();
    }

    private static void poll() {
        Student student = authService.auth();
        byte mark = 0;
        for (Object object : reader.read()) {
            Question question = (Question) object;
            writer.writeQuestion(question);
            String answer = ioService.readString();
            mark += checker.check(question, answer);
        }
        if (mark >= Byte.parseByte(passScore))
            ioService.out(student.getName() + " " + student.getSurname() + " passed the test successfully (" + mark + " correct answer out of 5)");
        else
            ioService.out(student.getName() + " " + student.getSurname() + " did not pass the test (" + mark + " correct answer out of 5)");
    }
}