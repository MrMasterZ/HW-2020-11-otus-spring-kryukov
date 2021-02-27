package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void create(Book bookObject);

    Optional<Book> getByBookId(Long bookId);

    List<Book> getAllBooks();

    void update(Book bookObject);

    void delete(Long bookId);

    List<Book> getByTitle(String title);

}