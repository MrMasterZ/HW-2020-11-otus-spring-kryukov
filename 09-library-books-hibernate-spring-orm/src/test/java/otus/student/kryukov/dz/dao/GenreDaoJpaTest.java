package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import otus.student.kryukov.dz.domain.Genre;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Genre.class")
@DataJpaTest
@Import(GenreDaoJpa.class)
public class GenreDaoJpaTest {

    private final GenreDao genreDao;
    private final TestEntityManager em;

    @Autowired
    public GenreDaoJpaTest(GenreDao genreDao, TestEntityManager em) {
        this.genreDao = genreDao;
        this.em = em;
    }

    @DisplayName("return Genre-row from database by genre-column")
    @Test
    void getByGenreTest() {
        Genre genreObject = genreDao.getByGenre("poem").get();
        assertAll(
                () -> assertEquals("poem", genreObject.getGenre()),
                () -> assertEquals(3L, genreObject.getGenreId())
        );
        assertEquals(Optional.empty(), genreDao.getByGenre("no such genre exists"), "Error getting Optional.empty() by nonexistent genre");
    }

    @DisplayName("return Genre-row from database by genre_id-column")
    @Test
    void getByGenreIdTest() {
        Genre genreObject = genreDao.getByGenreId(3L).get();
        assertAll(
                () -> assertEquals("poem", genreObject.getGenre()),
                () -> assertEquals(3L, genreObject.getGenreId())
        );
        assertEquals(Optional.empty(), genreDao.getByGenreId(-1L), "Error getting Optional.empty() by nonexistent genre_id");
    }

    @DisplayName("insert new Genre-row into table \"genres\" of database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createTest() {
        genreDao.create(new Genre("genretest"));
        assertEquals("genretest", genreDao.getByGenre("genretest").get().getGenre(), "Error creating Genre-row into table \"genres\" of database");
    }
}