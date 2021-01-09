package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Book;

import java.util.List;

public interface BookDao {

    long createBook(String title, String author, String genre);

    Book getByBookId(long bookId);

    List<Book> getAllBooks();

    void updateBook(long bookId, String title, String author, String genre);

    void deleteBook(long bookId);

}
