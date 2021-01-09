package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Book.class")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookDaoJdbcTest {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public BookDaoJdbcTest(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @DisplayName("insert new Book-row into table \"books\" of database and return his book_id")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createBookTest() {
        String title = "title test";
        String author = "Name Surname";
        String genre = "genretest";
        long bookId = bookDao.createBook(title, author, genre);
        Book bookObjectExpected = new Book(bookId, title, authorDao.getByAuthor(author).getAuthorId(), genreDao.getByGenre(genre).getGenreId());
        Book bookObjectGet = bookDao.getByBookId(bookId);
        assertEquals(bookObjectExpected, bookObjectGet, "Error creating Book-row");
    }

    @DisplayName("return Book-row from database by book_id-column")
    @Test
    void getByBookIdTest() {
        Book book = new Book(7L, "Ruslan and Lyudmila", 6L, 3L);
        assertEquals(book, bookDao.getByBookId(7L), "Error getting Book-row by book_id");
        assertNull(bookDao.getByBookId(-1L), "Error getting null by nonexistent book_id");
    }

    @DisplayName("return all Book-rows from database")
    @Test
    void getAllBooksTest() {
        List<Book> books = new ArrayList();
        books.add(new Book(1L, "Prelude to the foundation", 1L, 1L));
        books.add(new Book(2L, "Martian path", 1L, 1L));
        books.add(new Book(3L, "Space hospital", 2L, 1L));
        books.add(new Book(4L, "Seeded moon", 3L, 1L));
        books.add(new Book(5L, "Battle for suit", 4L, 2L));
        books.add(new Book(6L, "Breath of crisis", 5L, 2L));
        books.add(new Book(7L, "Ruslan and Lyudmila", 6L, 3L));
        books.add(new Book(8L, "Poltava", 6L, 3L));
        books.add(new Book(9L, "The tale of tsar Saltan", 6L, 4L));
        books.add(new Book(10L, "A tale of a fisherman and a fish", 6L, 4L));
        books.add(new Book(11L, "Dubrovsky", 6L, 5L));
        books.add(new Book(12L, "Boris Godunov", 6L, 6L));
        books.add(new Book(13L, "fiddle while Rome burns", 6L, 6L));
        books.add(new Book(14L, "About eternal peace", 6L, 7L));
        books.add(new Book(15L, "Russia and England in Central Asia", 7L, 8L));

        assertEquals(books, bookDao.getAllBooks(), "Error getting all Book-rows");
    }

    @DisplayName("update Book-row into table \"books\" of database (search row by book_id)")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void updateBookTest() {
        long bookId = 1L;
        String title = "title test";
        String author = "Name Surname";
        String genre = "genretest";
        bookDao.updateBook(bookId, title, author, genre);
        Book bookObjectExpected = new Book(bookId, title, authorDao.getByAuthor(author).getAuthorId(), genreDao.getByGenre(genre).getGenreId());
        Book bookObjectGet = bookDao.getByBookId(bookId);
        assertEquals(bookObjectExpected, bookObjectGet, "Error updating Book-row");
    }

    @DisplayName("delete Book-row from table \"books\" of database (search row by book_id)")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void deleteBookTest() {
        long bookId = 2L;
        bookDao.deleteBook(bookId);
        assertNull(bookDao.getByBookId(bookId), "Error deleting Book-row");
    }
}