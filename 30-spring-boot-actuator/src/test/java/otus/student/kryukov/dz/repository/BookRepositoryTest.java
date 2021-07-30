package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.student.kryukov.dz.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookRepository")
@DataJpaTest
public class BookRepositoryTest {

    private final BookRepository bookRepository;
    private final TestEntityManager em;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository, TestEntityManager em) {
        this.bookRepository = bookRepository;
        this.em = em;
    }

    @DisplayName("return all Book-rows from database")
    @Test
    void getAllBooksTest() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(15);
        assertAll(
                () -> assertEquals(1L, books.get(0).getId()),
                () -> assertEquals("Prelude to the foundation", books.get(0).getTitle()),
                () -> assertEquals(1L, books.get(0).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(0).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(0).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(0).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(2L, books.get(1).getId()),
                () -> assertEquals("Martian path", books.get(1).getTitle()),
                () -> assertEquals(1L, books.get(1).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(1).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(1).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(1).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(3L, books.get(2).getId()),
                () -> assertEquals("Space hospital", books.get(2).getTitle()),
                () -> assertEquals(2L, books.get(2).getAuthorObject().getAuthorId()),
                () -> assertEquals("James White", books.get(2).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(2).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(2).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(4L, books.get(3).getId()),
                () -> assertEquals("Seeded moon", books.get(3).getTitle()),
                () -> assertEquals(3L, books.get(3).getAuthorObject().getAuthorId()),
                () -> assertEquals("Connie Willis", books.get(3).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(3).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(3).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(5L, books.get(4).getId()),
                () -> assertEquals("Battle for suit", books.get(4).getTitle()),
                () -> assertEquals(4L, books.get(4).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Belov", books.get(4).getAuthorObject().getAuthor()),
                () -> assertEquals(2L, books.get(4).getGenreObject().getGenreId()),
                () -> assertEquals("detective story", books.get(4).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(6L, books.get(5).getId()),
                () -> assertEquals("Breath of crisis", books.get(5).getTitle()),
                () -> assertEquals(5L, books.get(5).getAuthorObject().getAuthorId()),
                () -> assertEquals("Evgeny Kukarkin", books.get(5).getAuthorObject().getAuthor()),
                () -> assertEquals(2L, books.get(5).getGenreObject().getGenreId()),
                () -> assertEquals("detective story", books.get(5).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(7L, books.get(6).getId()),
                () -> assertEquals("Ruslan and Lyudmila", books.get(6).getTitle()),
                () -> assertEquals(6L, books.get(6).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(6).getAuthorObject().getAuthor()),
                () -> assertEquals(3L, books.get(6).getGenreObject().getGenreId()),
                () -> assertEquals("poem", books.get(6).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(8L, books.get(7).getId()),
                () -> assertEquals("Poltava", books.get(7).getTitle()),
                () -> assertEquals(6L, books.get(7).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(7).getAuthorObject().getAuthor()),
                () -> assertEquals(3L, books.get(7).getGenreObject().getGenreId()),
                () -> assertEquals("poem", books.get(7).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(9L, books.get(8).getId()),
                () -> assertEquals("The tale of tsar Saltan", books.get(8).getTitle()),
                () -> assertEquals(6L, books.get(8).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(8).getAuthorObject().getAuthor()),
                () -> assertEquals(4L, books.get(8).getGenreObject().getGenreId()),
                () -> assertEquals("fairy tale", books.get(8).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(10L, books.get(9).getId()),
                () -> assertEquals("A tale of a fisherman and a fish", books.get(9).getTitle()),
                () -> assertEquals(6L, books.get(9).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(9).getAuthorObject().getAuthor()),
                () -> assertEquals(4L, books.get(9).getGenreObject().getGenreId()),
                () -> assertEquals("fairy tale", books.get(9).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(11L, books.get(10).getId()),
                () -> assertEquals("Dubrovsky", books.get(10).getTitle()),
                () -> assertEquals(6L, books.get(10).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(10).getAuthorObject().getAuthor()),
                () -> assertEquals(5L, books.get(10).getGenreObject().getGenreId()),
                () -> assertEquals("prose", books.get(10).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(12L, books.get(11).getId()),
                () -> assertEquals("Boris Godunov", books.get(11).getTitle()),
                () -> assertEquals(6L, books.get(11).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(11).getAuthorObject().getAuthor()),
                () -> assertEquals(6L, books.get(11).getGenreObject().getGenreId()),
                () -> assertEquals("drama", books.get(11).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(13L, books.get(12).getId()),
                () -> assertEquals("fiddle while Rome burns", books.get(12).getTitle()),
                () -> assertEquals(6L, books.get(12).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(12).getAuthorObject().getAuthor()),
                () -> assertEquals(6L, books.get(12).getGenreObject().getGenreId()),
                () -> assertEquals("drama", books.get(12).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(14L, books.get(13).getId()),
                () -> assertEquals("About eternal peace", books.get(13).getTitle()),
                () -> assertEquals(6L, books.get(13).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(13).getAuthorObject().getAuthor()),
                () -> assertEquals(7L, books.get(13).getGenreObject().getGenreId()),
                () -> assertEquals("journalism", books.get(13).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(15L, books.get(14).getId()),
                () -> assertEquals("Russia and England in Central Asia", books.get(14).getTitle()),
                () -> assertEquals(7L, books.get(14).getAuthorObject().getAuthorId()),
                () -> assertEquals("Terentyev Mikhail", books.get(14).getAuthorObject().getAuthor()),
                () -> assertEquals(8L, books.get(14).getGenreObject().getGenreId()),
                () -> assertEquals("history", books.get(14).getGenreObject().getGenre())
        );
    }

    @DisplayName("return list of Book-rows from database by title-column")
    @Test
    void getByTitleTest() {
        Book bookObect = bookRepository.findByTitle("Ruslan and Lyudmila").get(0);
        assertAll(
                () -> assertEquals(7L, bookObect.getId()),
                () -> assertEquals("Ruslan and Lyudmila", bookObect.getTitle()),
                () -> assertEquals("Alexander Pushkin", bookObect.getAuthorObject().getAuthor()),
                () -> assertEquals("poem", bookObect.getGenreObject().getGenre()));
    }
}