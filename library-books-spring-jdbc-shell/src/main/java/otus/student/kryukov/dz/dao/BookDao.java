package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.dto.BookDto;

import java.util.List;

public interface BookDao {

    long createBook(String title, String author, String genre);

    BookDto getByBookId(long bookId);

    List<BookDto> getAllBooks();

    void updateBook(long bookId, String title, String author, String genre);

    void deleteBook(long bookId);

}