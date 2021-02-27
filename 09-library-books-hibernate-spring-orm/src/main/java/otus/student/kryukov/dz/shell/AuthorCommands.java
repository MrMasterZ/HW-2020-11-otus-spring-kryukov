package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.AuthorService;

@ShellComponent
public class AuthorCommands {
    private final AuthorService authorService;
    private final PrintService printService;

    public AuthorCommands(AuthorService authorService, PrintService printService) {
        this.authorService = authorService;
        this.printService = printService;
    }

    @ShellMethod(value = "get author-object by author", key = {"ga", "getByAuthor"})
    public void getByAuthor(@ShellOption String author) {
        Author authorObject = authorService.getByAuthor(author)
                .orElseThrow(() -> new NoSuchEntityException("no such author exists"));
        authorService.drawAsciiTableAuthor(authorObject);
    }

    @ShellMethod(value = "get author-object by author_id", key = {"gaid", "getByAuthorId"})
    public void getByAuthorId(@ShellOption Long authorId) {
        Author authorObject = authorService.getByAuthorId(authorId)
                .orElseThrow(() -> new NoSuchEntityException("no such author exists"));
        authorService.drawAsciiTableAuthor(authorObject);
    }

    @ShellMethod(value = "create author-object in database", key = {"ca", "createByAuthor"})
    public void createByAuthor(@ShellOption String author) {
        authorService.create(author);
        printService.out("author created:");
        getByAuthor(author);
    }
}