package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book.class")
public class BookTest {

    @DisplayName("constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Book bookObject = new Book(7L, "Ruslan and Lyudmila", 6L, 3L);

        assertEquals(7L, bookObject.getBookId());
        assertEquals("Ruslan and Lyudmila", bookObject.getTitle());
        assertEquals(6L, bookObject.getAuthorId());
        assertEquals(3L, bookObject.getGenreId());
    }
}
