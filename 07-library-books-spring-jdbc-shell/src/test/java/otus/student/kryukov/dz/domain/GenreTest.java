package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Genre.class")
public class GenreTest {

    @DisplayName("constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Genre genreObject = new Genre(3L, "poem");

        assertEquals(3L, genreObject.getGenreId());
        assertEquals("poem", genreObject.getGenre());
    }
}
