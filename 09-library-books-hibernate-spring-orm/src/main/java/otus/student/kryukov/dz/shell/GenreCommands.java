package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.NoSuchEntityException;
import otus.student.kryukov.dz.print.DrawTable;
import otus.student.kryukov.dz.print.PrintService;
import otus.student.kryukov.dz.service.GenreService;

import java.util.Arrays;

@ShellComponent
public class GenreCommands {
    private final GenreService genreService;
    private final PrintService printService;
    private final DrawTable drawTable;

    public GenreCommands(GenreService genreService, PrintService printService, DrawTable drawTable) {
        this.genreService = genreService;
        this.printService = printService;
        this.drawTable = drawTable;
    }

    @ShellMethod(value = "get genre-object by genre", key = {"gg", "getByGenre"})
    public void getByGenre(@ShellOption String genre) {
        Genre genreObject = genreService.getByGenre(genre)
                .orElseThrow(() -> new NoSuchEntityException("no such genre exists"));
        drawTable.drawAsciiTableGenre(Arrays.asList(genreObject), Arrays.asList("genre_id", "genre"));
    }

    @ShellMethod(value = "get genre-object by genre_id", key = {"ggid", "getByGenreId"})
    public void getByGenreId(@ShellOption Long genreId) {
        Genre genreObject = genreService.getByGenreId(genreId)
                .orElseThrow(() -> new NoSuchEntityException("no such genre exists"));
        drawTable.drawAsciiTableGenre(Arrays.asList(genreObject), Arrays.asList("genre_id", "genre"));
    }

    @ShellMethod(value = "create genre-object in database", key = {"cg", "createByGenre"})
    public void createByGenre(@ShellOption String genre) {
        genreService.create(genre);
        printService.out("genre created:");
        getByGenre(genre);
    }
}