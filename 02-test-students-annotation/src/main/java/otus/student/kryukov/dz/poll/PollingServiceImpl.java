package otus.student.kryukov.dz.poll;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.auth.AuthService;
import otus.student.kryukov.dz.check.Checker;
import otus.student.kryukov.dz.dao.CSVStrategyReader;
import otus.student.kryukov.dz.dao.QuestionWriter;
import otus.student.kryukov.dz.domain.Question;
import otus.student.kryukov.dz.domain.Student;
import otus.student.kryukov.dz.io.IOService;

@Service
@PropertySource("classpath:application.properties")
public class PollingServiceImpl implements PollingService {
    private final String passScore;
    private final IOService ioService;
    private final AuthService authService;
    private final CSVStrategyReader reader;
    private final QuestionWriter writer;
    private final Checker checker;

    public PollingServiceImpl(@Value("${pass.score}") String passScore, IOService ioService, AuthService authService, CSVStrategyReader reader, QuestionWriter writer, Checker checker) {
        this.ioService = ioService;
        this.authService = authService;
        this.reader = reader;
        this.writer = writer;
        this.checker = checker;
        this.passScore = passScore;
    }

    @Override
    public void poll() {
        Student student = authService.auth();
        int mark = 0;
        int nQuestion = 0;
        for (Object object : reader.read()) {
            Question question = (Question) object;
            writer.writeQuestion(question);
            String answer = ioService.readString();
            mark += checker.check(question, answer);
            nQuestion++;
        }
        if (mark >= Integer.parseInt(passScore))
            ioService.out(student.getName() + " " + student.getSurname() + " passed the test successfully (" + mark + " correct answer out of " + nQuestion + ")");
        else
            ioService.out(student.getName() + " " + student.getSurname() + " did not pass the test (" + mark + " correct answer out of " + nQuestion + ")");
    }
}