package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import otus.student.kryukov.dz.domain.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BookRepository")
@DataMongoTest
public class BookRepositoryTest {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @DisplayName("return Book-rows from database by title-column")
    @Test
    void findByTitleTest() {
        List<Book> books = bookRepository.findByTitle("Prelude to the foundation");
        assertAll(
                () -> assertThat(books).hasSize(1),
                () -> assertEquals("Prelude to the foundation", books.get(0).getTitle()),
                () -> assertEquals("1", books.get(0).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(0).getAuthorObject().getAuthor()),
                () -> assertEquals("1", books.get(0).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(0).getGenreObject().getGenre())
        );
    }

    @DisplayName("return Book-rows from database by authorObject")
    @Test
    void findByAuthorObjectTest() {
        List<Book> books = bookRepository.findByAuthorObject(
                authorRepository.findById("1").get());
        assertAll(
                () -> assertThat(books).hasSize(2),
                () -> assertEquals("Prelude to the foundation", books.get(0).getTitle()),
                () -> assertEquals("1", books.get(0).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(0).getAuthorObject().getAuthor()),
                () -> assertEquals("1", books.get(0).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(0).getGenreObject().getGenre()),
                () -> assertEquals("Martian path", books.get(1).getTitle()),
                () -> assertEquals("1", books.get(1).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(1).getAuthorObject().getAuthor()),
                () -> assertEquals("1", books.get(1).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(1).getGenreObject().getGenre())
        );
    }
}