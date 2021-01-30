package otus.student.kryukov.dz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private Genre genreObject;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre getByGenre(String genre) {
        Map<String, String> params = Collections.singletonMap("genre", genre);
        try {
            genreObject = namedParameterJdbcOperations.queryForObject(
                    "select genre_id, genre from genres where genre = :genre", params, new GenreMapper()
            );
        } catch (Exception e) {
            genreObject = null;
        }
        return genreObject;
    }

    @Override
    public Genre getByGenreId(long genreId) {
        Map<String, Long> params = Collections.singletonMap("genre_id", genreId);
        try {
            genreObject = namedParameterJdbcOperations.queryForObject(
                    "select genre_id, genre from genres where genre_id = :genre_id", params, new GenreMapper()
            );
        } catch (Exception e) {
            genreObject = null;
        }
        return genreObject;
    }

    @Override
    public long createByGenre(String genre) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("genre", genre);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into genres (genre) values (:genre)", params, keyHolder, new String[]{"genre_id"}
        );
        return keyHolder.getKey().longValue();
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long genreId = resultSet.getLong("genre_id");
            String genre = resultSet.getString("genre");
            return new Genre(genreId, genre);
        }
    }
}