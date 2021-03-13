package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> getByGenre(String genre);

    Optional<Genre> getByGenreId(Long genreId);

    void create(Genre genreObject);

}