package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.BookService;

import java.util.List;

@ShellComponent
public class BookCommands {
    private final BookService bookService;
    private final PrintService printService;

    public BookCommands(BookService bookService, PrintService printService) {
        this.bookService = bookService;
        this.printService = printService;
    }

    @ShellMethod(value = "create book-object in database", key = {"cb", "createBook"})
    public void createBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookService.create(title, author, genre);
        printService.out("book created:");
        getBookByAllParams(title, author, genre);
    }

    @ShellMethod(value = "get book-object by book_id", key = {"gbid", "getByBookId"})
    public void getByBookId(@ShellOption Long bookId) {
        Book bookObject = bookService.getByBookId(bookId);
        bookService.drawAsciiTableBook(bookObject);
        bookService.drawCommentsForBook(bookObject);
    }

    @ShellMethod(value = "get all book-objects", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        bookService.drawAsciiTableBooks(books);
    }

    @ShellMethod(value = "update book-object in database", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption Long bookId, @ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookService.update(bookId, title, author, genre);
        printService.out("book updated:");
        getByBookId(bookId);
    }

    @ShellMethod(value = "delete book-object from database", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption Long bookId) {
        bookService.delete(bookId);
        printService.out("book deleted");
    }

    @ShellMethod(value = "get book-object by all params", key = {"gb", "getBookByAllParams"})
    public void getBookByAllParams(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        Book bookObject = bookService.getByAllParams(title, author, genre)
                .orElseThrow(() -> new NoSuchEntityException("no such book exists"));
        bookService.drawAsciiTableBook(bookObject);
    }
}