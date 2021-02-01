package otus.student.kryukov.dz.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.domain.dto.BookDto;

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
    public BookDto getByBookId(long bookId) {
        BookDto bookDto;
        Map<String, Long> params = Collections.singletonMap("book_id", bookId);
        try {
            bookDto = namedParameterJdbcOperations.queryForObject(
                    "select b.book_id, b.title, b.author_id, b.genre_id, a.author, g.genre " +
                            "from books b " +
                            "left join authors a ON a.author_id = b.author_id " +
                            "left join genres g ON g.genre_id = b.genre_id " +
                            "where b.book_id = :book_id", params, new BookDtoMapper()
            );
        } catch (Exception e) {
            bookDto = null;
        }
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return namedParameterJdbcOperations.query("select b.book_id, b.title, b.author_id, b.genre_id, a.author, g.genre " +
                                                        "from books b " +
                                                        "left join authors a ON a.author_id = b.author_id " +
                                                        "left join genres g ON g.genre_id = b.genre_id ",
                                                        new BookDtoMapper());
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

  private static class BookDtoMapper implements RowMapper<BookDto> {
      @Override
      public BookDto mapRow(ResultSet resultSet, int i) throws SQLException {
          Book bookObject = new Book(
                  resultSet.getLong("book_id"),
                  resultSet.getString("title"),
                  resultSet.getLong("author_id"),
                  resultSet.getLong("genre_id")
          );
          Author authorObject = new Author(
                  resultSet.getLong("author_id"),
                  resultSet.getString("author")
          );
          Genre genreObject = new Genre(
                  resultSet.getLong("genre_id"),
                  resultSet.getString("genre")
          );
          return new BookDto(bookObject, authorObject, genreObject);
      }
  }
}