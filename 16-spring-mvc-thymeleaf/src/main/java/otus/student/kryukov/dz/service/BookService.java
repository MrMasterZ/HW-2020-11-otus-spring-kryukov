package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void create(String title, String author, String genre);

    Book getByBookId(String bookId);

    List<Book> getAllBooks();

    void update(String bookId, String title, String author, String genre);

    void delete(String bookId);

    Optional<Book> getByAllParams(String title, String author, String genre);

    Boolean existsByAuthorId(String authorId);

}