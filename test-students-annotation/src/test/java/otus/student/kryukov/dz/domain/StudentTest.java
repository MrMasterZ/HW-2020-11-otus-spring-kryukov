package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Student")
public class StudentTest {

    @DisplayName("корректносоздаётсяконструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Student student = new Student("Sergey", "Kryukov");

        assertEquals("Sergey", student.getName());
        assertEquals("Kryukov", student.getSurname());
    }
}