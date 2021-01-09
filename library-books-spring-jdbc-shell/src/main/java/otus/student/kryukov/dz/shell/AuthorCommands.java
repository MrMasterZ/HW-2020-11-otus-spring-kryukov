package otus.student.kryukov.dz.shell;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.dao.AuthorDao;
import otus.student.kryukov.dz.domain.Author;

@ShellComponent
public class AuthorCommands {
    private final AuthorDao authorDao;

    public AuthorCommands(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @ShellMethod(value = "get author-object by author", key = {"ga", "getByAuthor"})
    public void getByAuthor(@ShellOption String author) {
        if (authorDao.getByAuthor(author) == null) System.out.println("no such author exists");
        else drawAsciiTableAuthor(authorDao.getByAuthor(author));
    }

    @ShellMethod(value = "get author-object by author_id", key = {"gaid", "getByAuthorId"})
    public void getByAuthorId(@ShellOption Long authorId) {
        if (authorDao.getByAuthorId(authorId) == null) System.out.println("no such author exists");
        else drawAsciiTableAuthor(authorDao.getByAuthorId(authorId));
    }

    @ShellMethod(value = "create author-object by author", key = {"ca", "createByAuthor"})
    public void createByAuthor(@ShellOption String author) {
        long authorId = authorDao.createByAuthor(author);
        System.out.println("author created:");
        drawAsciiTableAuthor(authorDao.getByAuthorId(authorId));
    }

    private void drawAsciiTableAuthor(Author author) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("author_id", "author");
        table.addRule();
        table.addRow(author.getAuthorId(), author.getAuthor());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        System.out.println(table.render());
    }
}