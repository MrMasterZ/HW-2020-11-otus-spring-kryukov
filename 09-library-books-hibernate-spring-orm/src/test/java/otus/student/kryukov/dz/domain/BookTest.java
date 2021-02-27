package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book.class")
public class BookTest {

    @DisplayName("book constructor-method with 3 args is correct")
    @Test
    void shouldHaveCorrectConstructorWith3Args() {
        Book bookObject = new Book( "Ruslan and Lyudmila",
                new Author("Alexander Belov"),
                new Genre("poem"));

        assertEquals("Ruslan and Lyudmila", bookObject.getTitle());
        assertEquals("Alexander Belov", bookObject.getAuthorObject().getAuthor());
        assertEquals("poem", bookObject.getGenreObject().getGenre());
    }

    @DisplayName("constructor-method with 4 args is correct")
    @Test
    void shouldHaveCorrectConstructorWith4Args() {
        Book bookObject = new Book( 7L,
                "Ruslan and Lyudmila",
                new Author("Alexander Belov"),
                new Genre("poem"));

        assertEquals(7L, bookObject.getBookId());
        assertEquals("Ruslan and Lyudmila", bookObject.getTitle());
        assertEquals("Alexander Belov", bookObject.getAuthorObject().getAuthor());
        assertEquals("poem", bookObject.getGenreObject().getGenre());
    }
}