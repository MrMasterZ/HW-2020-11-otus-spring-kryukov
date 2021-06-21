package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.DrawTable;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.AuthorService;

import java.util.Arrays;

@ShellComponent
public class AuthorCommands {
    private final AuthorService authorService;
    private final PrintService printService;
    private final DrawTable drawTable;

    public AuthorCommands(AuthorService authorService, PrintService printService, DrawTable drawTable) {
        this.authorService = authorService;
        this.printService = printService;
        this.drawTable = drawTable;
    }

    @ShellMethod(value = "get author-object by author", key = {"ga", "getByAuthor"})
    public void getByAuthor(@ShellOption String author) {
        Author authorObject = authorService.getByAuthor(author)
                .orElseThrow(() -> new NoSuchEntityException("no such author exists"));
        drawTable.drawAsciiTableAuthor(Arrays.asList(authorObject), Arrays.asList("author_id", "author"));
    }

    @ShellMethod(value = "get author-object by author_id", key = {"gaid", "getByAuthorId"})
    public void getByAuthorId(@ShellOption String authorId) {
        Author authorObject = authorService.getByAuthorId(authorId)
                .orElseThrow(() -> new NoSuchEntityException("no such author exists"));
        drawTable.drawAsciiTableAuthor(Arrays.asList(authorObject), Arrays.asList("author_id", "author"));
    }

    @ShellMethod(value = "create author-object in database", key = {"ca", "createByAuthor"})
    public void createByAuthor(@ShellOption String author) {
        authorService.create(author);
        printService.out("author created:");
        getByAuthor(author);
    }

    @ShellMethod(value = "delete author-object in database", key = {"da", "deleteAuthor"})
    public void deleteAthor(@ShellOption String author) {
        authorService.delete(author);
        printService.out("author deleted");
    }
}