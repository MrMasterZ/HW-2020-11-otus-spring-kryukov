package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Genre.class")
public class GenreTest {

    @DisplayName("constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Genre genre = new Genre(3L, "poem");

        assertEquals(3L, genre.getGenreId());
        assertEquals("poem", genre.getGenre());
    }
}
