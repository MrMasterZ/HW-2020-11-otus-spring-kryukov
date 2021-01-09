package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book.class")
public class BookTest {

    @DisplayName("constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Book book = new Book(7L, "Ruslan and Lyudmila", 6L, 3L);

        assertEquals(7L, book.getBookId());
        assertEquals("Ruslan and Lyudmila", book.getTitle());
        assertEquals(6L, book.getAuthorId());
        assertEquals(3L, book.getGenreId());
    }
}
