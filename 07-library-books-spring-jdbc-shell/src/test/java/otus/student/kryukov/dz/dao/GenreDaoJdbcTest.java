package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Genre.class")
@JdbcTest
@Import(GenreDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreDaoJdbcTest {
    private final GenreDao genreDao;

    @Autowired
    public GenreDaoJdbcTest(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @DisplayName("return Genre-row from database by genre-column")
    @Test
    void getByGenreTest() {
        Genre genreObject = new Genre(3L, "poem");
        assertEquals(genreObject, genreDao.getByGenre("poem"), "Error getting Genre-row by genre");
        assertNull(genreDao.getByGenre("sedfghjkmngfcvgbhjhgdesdft"), "Error getting null by nonexistent genre");
    }

    @DisplayName("return Genre-row from database by genre_id-column")
    @Test
    void getByGenreIdTest() {
        Genre genreObject = new Genre(3L, "poem");
        assertEquals(genreObject, genreDao.getByGenreId(3L), "Error getting Genre-row by genre_id");
        assertNull(genreDao.getByGenreId(-1L), "Error getting null by nonexistent genre_id");
    }

    @DisplayName("insert new Genre-row into table \"genres\" of database and return his genre_id")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createByGenreTest() {
        String genre = "genretest";
        long genreId = genreDao.createByGenre(genre);
        Genre genreObjectExpected = new Genre(genreId, genre);
        Genre genreObjectGet = genreDao.getByGenreId(genreId);
        assertEquals(genreObjectExpected, genreObjectGet, "Error creating Genre-row");
    }
}