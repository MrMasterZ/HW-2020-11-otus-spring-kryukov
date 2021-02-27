package otus.student.kryukov.dz.service;

import otus.student.kryukov.dz.domain.Genre;

import java.util.Optional;

public interface GenreService {

    Optional<Genre> getByGenre(String genre);

    Optional<Genre> getByGenreId(Long genreId);

    void create(String genre);

    void drawAsciiTableGenre(Genre genreObject);

    void checkEmptyGenre(String genre);

}