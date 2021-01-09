package otus.student.kryukov.dz.shell;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.dao.GenreDao;
import otus.student.kryukov.dz.domain.Genre;

@ShellComponent
public class GenreCommands {
    private final GenreDao genreDao;

    public GenreCommands(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @ShellMethod(value = "get genre-object by genre", key = {"gg", "getByGenre"})
    public void getByGenre(@ShellOption String genre) {
        if (genreDao.getByGenre(genre) == null) System.out.println("no such genre exists");
        else drawAsciiTableGenre(genreDao.getByGenre(genre));
    }

    @ShellMethod(value = "get genre-object by genre_id", key = {"ggid", "getByGenreId"})
    public void getByGenreId(@ShellOption Long genreId) {
        if (genreDao.getByGenreId(genreId) == null) System.out.println("no such genre exists");
        else drawAsciiTableGenre(genreDao.getByGenreId(genreId));
    }

    @ShellMethod(value = "create genre-object by genre", key = {"cg", "createByGenre"})
    public void createByGenre(@ShellOption String genre) {
        long genreId = genreDao.createByGenre(genre);
        System.out.println("genre created:");
        drawAsciiTableGenre(genreDao.getByGenreId(genreId));
    }

    private void drawAsciiTableGenre(Genre genre) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("genre_id", "genre");
        table.addRule();
        table.addRow(genre.getGenreId(), genre.getGenre());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        System.out.println(table.render());
    }
}