package otus.student.kryukov.dz.shell;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.dao.BookDao;
import otus.student.kryukov.dz.domain.dto.BookDto;

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
        getByBookId(bookId);
    }

    @ShellMethod(value = "get book-object by book_id", key = {"gbid", "getByBookId"})
    public void getByBookId(@ShellOption long bookId) {
        BookDto bookDto = bookDao.getByBookId(bookId);
        if (bookDto == null) System.out.println("no such book exists");
        else drawAsciiTableBook(bookDto);
    }

    @ShellMethod(value = "get all book-objects", key = {"gab", "getAllBooks"})
    public void getAllBooks() {
        drawAsciiTableBooks(bookDao.getAllBooks());
    }

    @ShellMethod(value = "update book-object, search by book_id", key = {"ub", "updateBook"})
    public void updateBook(@ShellOption long bookId, @ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        bookDao.updateBook(bookId, title, author, genre);
        System.out.println("book updated:");
        getByBookId(bookId);
    }

    @ShellMethod(value = "delete book-object, search by book_id", key = {"db", "deleteBook"})
    public void deleteBook(@ShellOption long bookId) {
        bookDao.deleteBook(bookId);
        System.out.println("book deleted");
    }

    private void drawAsciiTableBook(BookDto bookDto) {
        List<BookDto> bookDtos = new ArrayList();
        bookDtos.add(bookDto);
        drawAsciiTableBooks(bookDtos);
    }

    private void drawAsciiTableBooks(List<BookDto> bookDtos) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("book_id", "title", "author", "genre");
        table.addRule();
        for (BookDto bookDto : bookDtos) {
            table.addRow(
                    bookDto.getBookObject().getBookId(),
                    bookDto.getBookObject().getTitle(),
                    bookDto.getAuthorObject().getAuthor(),
                    bookDto.getGenreObject().getGenre()
            );
            table.addRule();
        }
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        System.out.println(table.render());
    }
}