package otus.student.kryukov.dz.print;

import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Comment;
import otus.student.kryukov.dz.domain.Genre;

import java.util.List;

public interface DrawTable {

    void drawAsciiTableAuthor(List<Author> authors, List<String> titles);

    void drawAsciiTableBook(List<Book> books, List<String> titles);

    void drawAsciiTableComment(List<Comment> comments, List<String> titles);

    void drawAsciiTableCommentsForBook(Book bookObject, List<String> titles);

    void drawAsciiTableGenre(List<Genre> genres, List<String> titles);
}