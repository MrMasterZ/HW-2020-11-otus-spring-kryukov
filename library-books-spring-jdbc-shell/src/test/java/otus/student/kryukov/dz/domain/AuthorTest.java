package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Author.class")
public class AuthorTest {

    @DisplayName("constructor-method is correct")
    @Test
    void shouldHaveCorrectConstructor() {
        Author authorObject = new Author(4L, "Alexander Belov");

        assertEquals(4L, authorObject.getAuthorId());
        assertEquals("Alexander Belov", authorObject.getAuthor());
    }
}
