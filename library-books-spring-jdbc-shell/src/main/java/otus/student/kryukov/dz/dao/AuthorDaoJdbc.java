package otus.student.kryukov.dz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private Author authorObject;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author getByAuthor(String author) {
        Map<String, String> params = Collections.singletonMap("author", author);
        try {
            authorObject = namedParameterJdbcOperations.queryForObject(
                    "select author_id, author from authors where author = :author", params, new AuthorMapper()
            );
        } catch (Exception e) {
            authorObject = null;
        }
        return authorObject;
    }

    @Override
    public Author getByAuthorId(long authorId) {
        Map<String, Long> params = Collections.singletonMap("author_id", authorId);
        try {
            authorObject = namedParameterJdbcOperations.queryForObject(
                    "select author_id, author from authors where author_id = :author_id", params, new AuthorMapper()
            );
        } catch (Exception e) {
            authorObject = null;
        }
        return authorObject;
    }

    @Override
    public long createByAuthor(String author) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("author", author);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into authors (author) values (:author)", params, keyHolder, new String[]{"author_id"}
        );
        return keyHolder.getKey().longValue();
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("author_id");
            String author = resultSet.getString("author");
            return new Author(authorId, author);
        }
    }
}