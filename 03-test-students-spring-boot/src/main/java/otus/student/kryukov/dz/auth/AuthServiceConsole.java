package otus.student.kryukov.dz.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.configs.AppProps;
import otus.student.kryukov.dz.domain.Student;
import otus.student.kryukov.dz.io.IOService;

@Service
@EnableConfigurationProperties(AppProps.class)
public class AuthServiceConsole implements AuthService {
    private final IOService ioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceConsole.class);
    private final MessageSource messageSource;
    private final AppProps props;

    public AuthServiceConsole(IOService ioService, AppProps props, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public Student auth() {
        LOGGER.info("AuthServiceConsole.auth()");
        return new Student(enterName(), enterSurname());
    }

    private String enterName() {
        ioService.out(messageSource.getMessage("name.user", null, props.getLocale()));
        return ioService.readString();
    }

    private String enterSurname() {
        ioService.out(messageSource.getMessage("surname.user", null, props.getLocale()));
        return ioService.readString();
    }
}