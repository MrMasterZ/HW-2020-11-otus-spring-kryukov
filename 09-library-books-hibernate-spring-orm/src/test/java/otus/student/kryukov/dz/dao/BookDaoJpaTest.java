package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Book.class")
@DataJpaTest
@Import(BookDaoJpa.class)
public class BookDaoJpaTest {

    private final BookDao bookDao;
    private final TestEntityManager em;

    @Autowired
    public BookDaoJpaTest(BookDao bookDao, TestEntityManager em) {
        this.bookDao = bookDao;
        this.em = em;
    }

    @DisplayName("insert new Book-row into table \"books\" of database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createTest() {
        Book bookObject = new Book("title test", em.find(Author.class, 6L), em.find(Genre.class, 3L));
        bookDao.create(bookObject);
        Book bookObjectGet = bookDao.getByTitle("title test").get(0);
        assertAll(
                () -> assertEquals("title test", bookObjectGet.getTitle()),
                () -> assertEquals("Alexander Pushkin", bookObjectGet.getAuthorObject().getAuthor()),
                () -> assertEquals("poem", bookObjectGet.getGenreObject().getGenre())
        );
    }

    @DisplayName("return Book-row from database by book_id-column")
    @Test
    void getByBookIdTest() {
        Book bookObject = bookDao.getByBookId(7L).get();
        assertAll(
                () -> assertEquals(7L, bookObject.getBookId()),
                () -> assertEquals("Ruslan and Lyudmila", bookObject.getTitle()),
                () -> assertEquals(6L, bookObject.getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", bookObject.getAuthorObject().getAuthor()),
                () -> assertEquals(3L, bookObject.getGenreObject().getGenreId()),
                () -> assertEquals("poem", bookObject.getGenreObject().getGenre())
        );
        assertEquals(Optional.empty(), bookDao.getByBookId(-1L), "Error getting Optional.empty() by nonexistent book_id");
    }

    @DisplayName("return all Book-rows from database")
    @Test
    void getAllBooksTest() {
        List<Book> books = bookDao.getAllBooks();
        assertThat(books).hasSize(15);
        assertAll(
                () -> assertEquals(1L, books.get(0).getBookId()),
                () -> assertEquals("Prelude to the foundation", books.get(0).getTitle()),
                () -> assertEquals(1L, books.get(0).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(0).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(0).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(0).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(2L, books.get(1).getBookId()),
                () -> assertEquals("Martian path", books.get(1).getTitle()),
                () -> assertEquals(1L, books.get(1).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(1).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(1).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(1).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(3L, books.get(2).getBookId()),
                () -> assertEquals("Space hospital", books.get(2).getTitle()),
                () -> assertEquals(2L, books.get(2).getAuthorObject().getAuthorId()),
                () -> assertEquals("James White", books.get(2).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(2).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(2).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(4L, books.get(3).getBookId()),
                () -> assertEquals("Seeded moon", books.get(3).getTitle()),
                () -> assertEquals(3L, books.get(3).getAuthorObject().getAuthorId()),
                () -> assertEquals("Connie Willis", books.get(3).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(3).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(3).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(5L, books.get(4).getBookId()),
                () -> assertEquals("Battle for suit", books.get(4).getTitle()),
                () -> assertEquals(4L, books.get(4).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Belov", books.get(4).getAuthorObject().getAuthor()),
                () -> assertEquals(2L, books.get(4).getGenreObject().getGenreId()),
                () -> assertEquals("detective story", books.get(4).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(6L, books.get(5).getBookId()),
                () -> assertEquals("Breath of crisis", books.get(5).getTitle()),
                () -> assertEquals(5L, books.get(5).getAuthorObject().getAuthorId()),
                () -> assertEquals("Evgeny Kukarkin", books.get(5).getAuthorObject().getAuthor()),
                () -> assertEquals(2L, books.get(5).getGenreObject().getGenreId()),
                () -> assertEquals("detective story", books.get(5).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(7L, books.get(6).getBookId()),
                () -> assertEquals("Ruslan and Lyudmila", books.get(6).getTitle()),
                () -> assertEquals(6L, books.get(6).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(6).getAuthorObject().getAuthor()),
                () -> assertEquals(3L, books.get(6).getGenreObject().getGenreId()),
                () -> assertEquals("poem", books.get(6).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(8L, books.get(7).getBookId()),
                () -> assertEquals("Poltava", books.get(7).getTitle()),
                () -> assertEquals(6L, books.get(7).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(7).getAuthorObject().getAuthor()),
                () -> assertEquals(3L, books.get(7).getGenreObject().getGenreId()),
                () -> assertEquals("poem", books.get(7).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(9L, books.get(8).getBookId()),
                () -> assertEquals("The tale of tsar Saltan", books.get(8).getTitle()),
                () -> assertEquals(6L, books.get(8).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(8).getAuthorObject().getAuthor()),
                () -> assertEquals(4L, books.get(8).getGenreObject().getGenreId()),
                () -> assertEquals("fairy tale", books.get(8).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(10L, books.get(9).getBookId()),
                () -> assertEquals("A tale of a fisherman and a fish", books.get(9).getTitle()),
                () -> assertEquals(6L, books.get(9).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(9).getAuthorObject().getAuthor()),
                () -> assertEquals(4L, books.get(9).getGenreObject().getGenreId()),
                () -> assertEquals("fairy tale", books.get(9).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(11L, books.get(10).getBookId()),
                () -> assertEquals("Dubrovsky", books.get(10).getTitle()),
                () -> assertEquals(6L, books.get(10).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(10).getAuthorObject().getAuthor()),
                () -> assertEquals(5L, books.get(10).getGenreObject().getGenreId()),
                () -> assertEquals("prose", books.get(10).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(12L, books.get(11).getBookId()),
                () -> assertEquals("Boris Godunov", books.get(11).getTitle()),
                () -> assertEquals(6L, books.get(11).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(11).getAuthorObject().getAuthor()),
                () -> assertEquals(6L, books.get(11).getGenreObject().getGenreId()),
                () -> assertEquals("drama", books.get(11).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(13L, books.get(12).getBookId()),
                () -> assertEquals("fiddle while Rome burns", books.get(12).getTitle()),
                () -> assertEquals(6L, books.get(12).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(12).getAuthorObject().getAuthor()),
                () -> assertEquals(6L, books.get(12).getGenreObject().getGenreId()),
                () -> assertEquals("drama", books.get(12).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(14L, books.get(13).getBookId()),
                () -> assertEquals("About eternal peace", books.get(13).getTitle()),
                () -> assertEquals(6L, books.get(13).getAuthorObject().getAuthorId()),
                () -> assertEquals("Alexander Pushkin", books.get(13).getAuthorObject().getAuthor()),
                () -> assertEquals(7L, books.get(13).getGenreObject().getGenreId()),
                () -> assertEquals("journalism", books.get(13).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(15L, books.get(14).getBookId()),
                () -> assertEquals("Russia and England in Central Asia", books.get(14).getTitle()),
                () -> assertEquals(7L, books.get(14).getAuthorObject().getAuthorId()),
                () -> assertEquals("Terentyev Mikhail", books.get(14).getAuthorObject().getAuthor()),
                () -> assertEquals(8L, books.get(14).getGenreObject().getGenreId()),
                () -> assertEquals("history", books.get(14).getGenreObject().getGenre())
        );
    }

    @DisplayName("update Book-row into table \"books\" of database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void updateTest() {
        Book bookObject = new Book(8L, "title test 2", em.find(Author.class, 6L), em.find(Genre.class, 3L));
        bookDao.update(bookObject);
        Book bookObjectGet = bookDao.getByBookId(8L).get();
        assertAll(
                () -> assertEquals("title test 2", bookObjectGet.getTitle()),
                () -> assertEquals("Alexander Pushkin", bookObjectGet.getAuthorObject().getAuthor()),
                () -> assertEquals("poem", bookObjectGet.getGenreObject().getGenre())
        );
    }

    @DisplayName("delete Book-row from table \"books\" of database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void deleteTest() {
        bookDao.delete(1L);
        assertEquals(Optional.empty(), bookDao.getByBookId(1L), "Error deleting of Book-row from table \"books\" of database");
    }

    @DisplayName("return list of Book-rows from database by title-column")
    @Test
    void getByTitleTest() {
        List<Book> books = bookDao.getByTitle("Ruslan and Lyudmila");
        Book bookObject = new Book(7L, "Ruslan and Lyudmila", em.find(Author.class, 6L), em.find(Genre.class, 3L));
        assertTrue(books.contains(bookObject), "Error getting book-row by title");
    }
}