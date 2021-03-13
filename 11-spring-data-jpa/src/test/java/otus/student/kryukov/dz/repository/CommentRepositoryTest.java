package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CommentRepository")
@DataJpaTest
public class CommentRepositoryTest {

    private final CommentRepository commentRepository;
    private final TestEntityManager em;

    @Autowired
    public CommentRepositoryTest(CommentRepository commentRepository, TestEntityManager em) {
        this.commentRepository = commentRepository;
        this.em = em;
    }

    @DisplayName("return all Comment-rows from database")
    @Test
    void getAllCommentsTest() {
        List<Comment> comments = commentRepository.findAllFetch();
        assertThat(comments).hasSize(20);
        assertAll(
                () -> assertEquals(1L, comments.get(0).getCommentId()),
                () -> assertEquals("Exciting science fiction, but rather predictable plot moves.", comments.get(0).getComment()),
                () -> assertEquals(1L, comments.get(0).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(2L, comments.get(1).getCommentId()),
                () -> assertEquals("about how our planet will look in millions of years", comments.get(1).getComment()),
                () -> assertEquals(1L, comments.get(1).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(3L, comments.get(2).getCommentId()),
                () -> assertEquals("For admiration, I could not sleep quietly for several days", comments.get(2).getComment()),
                () -> assertEquals(2L, comments.get(2).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(4L, comments.get(3).getCommentId()),
                () -> assertEquals("A perfectly invented Galaxy of the Future", comments.get(3).getComment()),
                () -> assertEquals(3L, comments.get(3).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(5L, comments.get(4).getCommentId()),
                () -> assertEquals("The author was a graphoman", comments.get(4).getComment()),
                () -> assertEquals(3L, comments.get(4).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(6L, comments.get(5).getCommentId()),
                () -> assertEquals("Recommend reading from insomnia", comments.get(5).getComment()),
                () -> assertEquals(4L, comments.get(5).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(7L, comments.get(6).getCommentId()),
                () -> assertEquals("Interesting", comments.get(6).getComment()),
                () -> assertEquals(5L, comments.get(6).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(8L, comments.get(7).getCommentId()),
                () -> assertEquals("useful for obtaining new information", comments.get(7).getComment()),
                () -> assertEquals(6L, comments.get(7).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(9L, comments.get(8).getCommentId()),
                () -> assertEquals("Immortal classic", comments.get(8).getComment()),
                () -> assertEquals(7L, comments.get(8).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(10L, comments.get(9).getCommentId()),
                () -> assertEquals("Boringly", comments.get(9).getComment()),
                () -> assertEquals(7L, comments.get(9).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(11L, comments.get(10).getCommentId()),
                () -> assertEquals("Insanely interesting", comments.get(10).getComment()),
                () -> assertEquals(7L, comments.get(10).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(12L, comments.get(11).getCommentId()),
                () -> assertEquals("High poetic style.", comments.get(11).getComment()),
                () -> assertEquals(8L, comments.get(11).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(13L, comments.get(12).getCommentId()),
                () -> assertEquals("Beauty of words, lyricism and charm of heroes", comments.get(12).getComment()),
                () -> assertEquals(9L, comments.get(12).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(14L, comments.get(13).getCommentId()),
                () -> assertEquals("Suitable for children of all ages.", comments.get(13).getComment()),
                () -> assertEquals(10L, comments.get(13).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(15L, comments.get(14).getCommentId()),
                () -> assertEquals("Instructive tale", comments.get(14).getComment()),
                () -> assertEquals(10L, comments.get(14).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(16L, comments.get(15).getCommentId()),
                () -> assertEquals("Deep meaning", comments.get(15).getComment()),
                () -> assertEquals(11L, comments.get(15).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(17L, comments.get(16).getCommentId()),
                () -> assertEquals("Historical work is accompanied by chronological events", comments.get(16).getComment()),
                () -> assertEquals(12L, comments.get(16).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(18L, comments.get(17).getCommentId()),
                () -> assertEquals("Can be read at leisure", comments.get(17).getComment()),
                () -> assertEquals(13L, comments.get(17).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(19L, comments.get(18).getCommentId()),
                () -> assertEquals("Difficult to perceive", comments.get(18).getComment()),
                () -> assertEquals(14L, comments.get(18).getBookObject().getBookId())
        );
        assertAll(
                () -> assertEquals(20L, comments.get(19).getCommentId()),
                () -> assertEquals("The work of one of the best domestic orientalists of the 19th century", comments.get(19).getComment()),
                () -> assertEquals(15L, comments.get(19).getBookObject().getBookId())
        );
    }

    @DisplayName("return list of Comment-rows from database by comment-column")
    @Test
    void getByCommentTest() {
        List<Comment> comments = commentRepository.findByComment("For admiration, I could not sleep quietly for several days");
        Comment commentObject = new Comment(3L,
                "For admiration, I could not sleep quietly for several days",
                em.find(Book.class, 2L));
        assertTrue(comments.contains(commentObject), "Error getting comment-row by comment");
    }

}