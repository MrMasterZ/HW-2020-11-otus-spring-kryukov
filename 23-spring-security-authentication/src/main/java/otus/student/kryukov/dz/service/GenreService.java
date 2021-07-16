package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> getByGenre(String genre);

    Optional<Genre> getByGenreId(String genreId);

    List<Genre> getAllGenres();

    void create(String genre);

    void checkEmptyGenre(String genre);

}