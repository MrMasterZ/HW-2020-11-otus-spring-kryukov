package otus.student.kryukov.dz.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Student;
import otus.student.kryukov.dz.io.IOService;

@Service
public class AuthServiceConsole implements AuthService {
    private static IOService ioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceConsole.class);

    public AuthServiceConsole(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student auth() {
        LOGGER.info("AuthServiceConsole.auth()");
        return new Student(enterName(), enterSurname());
    }

    private synchronized static String enterName() {
        ioService.out("Enter your name:");
        return ioService.readString();
    }

    private synchronized static String enterSurname() {
        ioService.out("Enter your surname:");
        return ioService.readString();
    }
}