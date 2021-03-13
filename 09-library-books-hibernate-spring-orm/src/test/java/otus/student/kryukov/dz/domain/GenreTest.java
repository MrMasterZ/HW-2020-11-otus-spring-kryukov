package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Genre.class")
public class GenreTest {

    @DisplayName("genre constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Genre genreObject = new Genre("poem");

        assertEquals("poem", genreObject.getGenre());
    }
}