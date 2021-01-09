package otus.student.kryukov.dz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public long createBook(String title, String author, String genre) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("author_id", getAuthorId(author))
                .addValue("genre_id", getGenreId(genre));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)", params, keyHolder, new String[]{"book_id"}
        );
        return keyHolder.getKey().longValue();
    }

    @Override
    public Book getByBookId(long bookId) {
        Book bookObject;
        Map<String, Long> params = Collections.singletonMap("book_id", bookId);
        try {
            bookObject = namedParameterJdbcOperations.queryForObject(
                    "select * from books where book_id = :book_id", params, new BookMapper()
            );
        } catch (Exception e) {
            bookObject = null;
        }
        return bookObject;
    }

    @Override
    public List<Book> getAllBooks() {
        return namedParameterJdbcOperations.query("select * from books", new BookMapper());
    }

    @Override
    public void updateBook(long bookId, String title, String author, String genre) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", bookId)
                .addValue("title", title)
                .addValue("author_id", getAuthorId(author))
                .addValue("genre_id", getGenreId(genre));
        namedParameterJdbcOperations.update(
                "update books set title = :title, author_id = :author_id, genre_id = :genre_id where book_id = :book_id", params
        );
    }

    @Override
    public void deleteBook(long bookId) {
        Map<String, Long> params = Collections.singletonMap("book_id", bookId);
        namedParameterJdbcOperations.update(
                "delete from books where book_id = :book_id", params
        );
    }

    private long getAuthorId(String author) {
        long authorId;
        if (authorDao.getByAuthor(author) == null) authorId = authorDao.createByAuthor(author);
        else authorId = authorDao.getByAuthor(author).getAuthorId();
        return authorId;
    }

    private long getGenreId(String genre) {
        long genreId;
        if (genreDao.getByGenre(genre) == null) genreId = genreDao.createByGenre(genre);
        else genreId = genreDao.getByGenre(genre).getGenreId();
        return genreId;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("book_id");
            String title = resultSet.getString("title");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            return new Book(bookId, title, authorId, genreId);
        }
    }
}