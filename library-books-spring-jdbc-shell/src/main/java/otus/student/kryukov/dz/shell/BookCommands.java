package otus.student.kryukov.dz.shell;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.dao.BookDao;
import otus.student.kryukov.dz.domain.Book;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class BookCommands {
    private final BookDao bookDao;

    public BookCommands(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @ShellMethod(value = "create book-object", key = {"cb", "createBook"})
    public void createBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        long bookId = bookDao.createBook(title, author, genre);
        System.out.println("book created:");
        List<Book> books = new ArrayList();
        books.add(bookDao.getByBookId(bookId));
        drawAsciiTableBooks(books);
    }

    @ShellMethod(value = "get book-object by book_id", key = {"gbid", "getByBookId"})
    public void getByBookId(@ShellOption long bookId) {
        if (bookDao.getByBookId(bookId) == null) System.out.println("no such book exists");
        else {
            List<Book> books = new ArrayList();
            books.add(bookDao.getByBookId(bookId));
            drawAsciiTableBooks(books);
        }
    }

    @ShellMethod(value = "get all book-objects", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        drawAsciiTableBooks(bookDao.getAllBooks());
    }

    @ShellMethod(value = "update book-object, search by book_id", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption long bookId, @ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookDao.updateBook(bookId, title, author, genre);
        System.out.println("book updated:");
        List<Book> books = new ArrayList();
        books.add(bookDao.getByBookId(bookId));
        drawAsciiTableBooks(books);
    }

    @ShellMethod(value = "delete book-object, search by book_id", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption long bookId) {
        bookDao.deleteBook(bookId);
        System.out.println("book deleted");
    }

    private void drawAsciiTableBooks(List<Book> books) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("book_id", "title", "author_id", "genre_id");
        table.addRule();
        for (Book book : books) {
            table.addRow(book.getBookId(), book.getTitle(), book.getAuthorId(), book.getGenreId());
            table.addRule();
        }
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        System.out.println(table.render());
    }
}