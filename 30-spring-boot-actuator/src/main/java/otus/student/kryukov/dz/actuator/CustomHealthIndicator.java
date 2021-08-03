package otus.student.kryukov.dz.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import otus.student.kryukov.dz.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class CustomHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        if(bookRepository.findAll().size() > 0) {
            return Health.up()
                    .status(Status.UP)
                    .withDetail("message", "OK")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "there is no book in the library")
                    .build();
        }
    }
}