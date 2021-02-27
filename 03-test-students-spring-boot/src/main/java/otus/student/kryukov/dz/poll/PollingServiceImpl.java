package otus.student.kryukov.dz.poll;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.auth.AuthService;
import otus.student.kryukov.dz.check.Checker;
import otus.student.kryukov.dz.configs.AppProps;
import otus.student.kryukov.dz.dao.CSVStrategyReader;
import otus.student.kryukov.dz.dao.QuestionWriter;
import otus.student.kryukov.dz.domain.Question;
import otus.student.kryukov.dz.domain.Student;
import otus.student.kryukov.dz.io.IOService;

@Service
@EnableConfigurationProperties(AppProps.class)
public class PollingServiceImpl implements PollingService {
    private final IOService ioService;
    private final AuthService authService;
    private final CSVStrategyReader reader;
    private final QuestionWriter writer;
    private final Checker checker;
    private final AppProps props;
    private final MessageSource messageSource;

    public PollingServiceImpl(IOService ioService, AuthService authService, CSVStrategyReader reader, QuestionWriter writer, Checker checker, AppProps props, MessageSource messageSource) {
        this.ioService = ioService;
        this.authService = authService;
        this.reader = reader;
        this.writer = writer;
        this.checker = checker;
        this.props = props;
        this.messageSource = messageSource;
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
        if (mark >= props.getScorepass())
            ioService.out(student.getName() + " " + student.getSurname() + " " + messageSource.getMessage("test.success", new String[]{String.valueOf(mark), String.valueOf(nQuestion)}, props.getLocale()));
        else
            ioService.out(student.getName() + " " + student.getSurname() + " " + messageSource.getMessage("test.fail", new String[]{String.valueOf(mark), String.valueOf(nQuestion)}, props.getLocale()));
    }
}