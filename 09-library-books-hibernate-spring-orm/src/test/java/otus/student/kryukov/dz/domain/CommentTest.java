package otus.student.kryukov.dz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Comment.class")
public class CommentTest {

    @DisplayName("comment constructor-method with 2 args is correct")
    @Test
    void shouldHaveCorrectConstructorWith2rgs() {
        Book bookObject = new Book( "Ruslan and Lyudmila",
                new Author("Alexander Belov"),
                new Genre("poem"));
        Comment commentObject = new Comment( "interesting", bookObject);

        assertEquals("interesting", commentObject.getComment());
        assertEquals(bookObject, commentObject.getBookObject());
    }

    @DisplayName("comment constructor-method with 2 args is correct")
    @Test
    void shouldHaveCorrectConstructorWith3Args() {
        Book bookObject = new Book( "Ruslan and Lyudmila",
                new Author("Alexander Belov"),
                new Genre("poem"));
        Comment commentObject = new Comment( 3L,"interesting", bookObject);

        assertEquals(3L, commentObject.getCommentId());
        assertEquals("interesting", commentObject.getComment());
        assertEquals(bookObject, commentObject.getBookObject());
    }
}