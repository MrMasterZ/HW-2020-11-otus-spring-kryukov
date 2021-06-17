package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.DrawTable;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.BookService;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class BookCommands {
    private final BookService bookService;
    private final PrintService printService;
    private final DrawTable drawTable;

    public BookCommands(BookService bookService, PrintService printService, DrawTable drawTable) {
        this.bookService = bookService;
        this.printService = printService;
        this.drawTable = drawTable;
    }

    @ShellMethod(value = "create book-object in database", key = {"cb", "createBook"})
    public void createBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookService.create(title, author, genre);
        printService.out("book created:");
        getBookByAllParams(title, author, genre);
    }

    @ShellMethod(value = "get book-object by book_id", key = {"gbid", "getByBookId"})
    public void getByBookId(@ShellOption String bookId) {
        Book bookObject = bookService.getByBookId(bookId);
        drawTable.drawAsciiTableBook(Arrays.asList(bookObject), Arrays.asList("book_id", "title", "author", "genre"));
    }

    @ShellMethod(value = "get all book-objects", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        drawTable.drawAsciiTableBook(books, Arrays.asList("book_id", "title", "author", "genre"));
    }

    @ShellMethod(value = "update book-object in database", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption String bookId, @ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookService.update(bookId, title, author, genre);
        printService.out("book updated:");
        getByBookId(bookId);
    }

    @ShellMethod(value = "delete book-object from database", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption String bookId) {
        bookService.delete(bookId);
        printService.out("book deleted");
    }

    @ShellMethod(value = "get book-object by all params", key = {"gb", "getBookByAllParams"})
    public void getBookByAllParams(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        Book bookObject = bookService.getByAllParams(title, author, genre)
                .orElseThrow(() -> new NoSuchEntityException("no such book exists"));
        drawTable.drawAsciiTableBook(Arrays.asList(bookObject), Arrays.asList("book_id", "title", "author", "genre"));
    }

    @ShellMethod(value = "check for existence book-object by author_id", key = {"ea", "existsAuthor"})
    public void existsByAuthorId(@ShellOption String authorId) {
        if(bookService.existsByAuthorId(authorId))
            printService.out("book with such an authorId exists");
        else
            printService.out("book with such an authorId does not exist");
    }
}