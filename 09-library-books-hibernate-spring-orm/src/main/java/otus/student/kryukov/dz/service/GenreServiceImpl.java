package otus.student.kryukov.dz.service;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.dao.GenreDao;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.print.PrintService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final PrintService printService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getByGenre(String genre) {
        return genreDao.getByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getByGenreId(Long genreId) {
        return genreDao.getByGenreId(genreId);
    }

    @Override
    @Transactional
    public void create(String genre) {
        checkEmptyGenre(genre);
        genreDao.create(new Genre(genre));
    }

    @Override
    public void drawAsciiTableGenre(Genre genreObject) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("genre_id", "genre");
        table.addRule();
        table.addRow(genreObject.getGenreId(), genreObject.getGenre());
        table.addRule();
        table.setTextAlignment(TextAlignment.CENTER);
        table.getRenderer().setCWC(new CWC_LongestLine());
        printService.out(table.render());
    }

    @Override
    public void checkEmptyGenre(String genre) {
        if (genre == null || genre.trim().equals(""))
            throw new EmptyEntityInsertException("genre cannot be empty");
    }

}