package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.domain.dto.BookDto;

import java.util.Arrays;
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
        Book bookObjectGet = bookDao.getByBookId(bookId).getBookObject();
        assertEquals(bookObjectExpected, bookObjectGet, "Error creating Book-row");
    }

    @DisplayName("return BookDto from databases (books, authors, genres) by book_id-column")
    @Test
    void getByBookIdTest() {
        BookDto bookDto = new BookDto(
                new Book(7L, "Ruslan and Lyudmila", 6L, 3L),
                new Author(6L, "Alexander Pushkin"),
                new Genre(3L, "poem")
        );

        assertEquals(bookDto, bookDao.getByBookId(7L), "Error getting BookDto by book_id");
        assertNull(bookDao.getByBookId(-1L), "Error getting null by nonexistent book_id");
    }

    @DisplayName("return all BookDto from databases (books, authors, genres)")
    @Test
    void getAllBooksTest() {
        List<BookDto> bookDtoList = Arrays.asList(
                new BookDto(
                        new Book(1L, "Prelude to the foundation", 1L, 1L),
                        new Author(1L, "Isaac Asimov"),
                        new Genre(1L, "fantasy")
                ),
                new BookDto(
                        new Book(2L, "Martian path", 1L, 1L),
                        new Author(1L, "Isaac Asimov"),
                        new Genre(1L, "fantasy")
                ),
                new BookDto(
                        new Book(3L, "Space hospital", 2L, 1L),
                        new Author(2L, "James White"),
                        new Genre(1L, "fantasy")
                ),
                new BookDto(
                        new Book(4L, "Seeded moon", 3L, 1L),
                        new Author(3L, "Connie Willis"),
                        new Genre(1L, "fantasy")
                ),
                new BookDto(
                        new Book(5L, "Battle for suit", 4L, 2L),
                        new Author(4L, "Alexander Belov"),
                        new Genre(2L, "detective story")
                ),
                new BookDto(
                        new Book(6L, "Breath of crisis", 5L, 2L),
                        new Author(5L, "Evgeny Kukarkin"),
                        new Genre(2L, "detective story")
                ),
                new BookDto(
                        new Book(7L, "Ruslan and Lyudmila", 6L, 3L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(3L, "poem")
                ),
                new BookDto(
                        new Book(8L, "Poltava", 6L, 3L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(3L, "poem")
                ),
                new BookDto(
                        new Book(9L, "The tale of tsar Saltan", 6L, 4L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(4L, "fairy tale")
                ),
                new BookDto(
                        new Book(10L, "A tale of a fisherman and a fish", 6L, 4L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(4L, "fairy tale")
                ),
                new BookDto(
                        new Book(11L, "Dubrovsky", 6L, 5L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(5L, "prose")
                ),
                new BookDto(
                        new Book(12L, "Boris Godunov", 6L, 6L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(6L, "drama")
                ),
                new BookDto(
                        new Book(13L, "fiddle while Rome burns", 6L, 6L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(6L, "drama")
                ),
                new BookDto(
                        new Book(14L, "About eternal peace", 6L, 7L),
                        new Author(6L, "Alexander Pushkin"),
                        new Genre(7L, "journalism")
                ),
                new BookDto(
                        new Book(15L, "Russia and England in Central Asia", 7L, 8L),
                        new Author(7L, "Terentyev Mikhail"),
                        new Genre(8L, "history")
                ));
        assertEquals(bookDtoList, bookDao.getAllBooks(), "Error getting all BookDto");
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
        Book bookObjectGet = bookDao.getByBookId(bookId).getBookObject();
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