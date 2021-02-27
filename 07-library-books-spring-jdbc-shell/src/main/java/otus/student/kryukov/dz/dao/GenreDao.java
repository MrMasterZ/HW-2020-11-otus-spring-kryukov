package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Genre;

public interface GenreDao {

    Genre getByGenre(String genre);

    Genre getByGenreId(long genreId);

    long createByGenre(String genre);
}
