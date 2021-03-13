package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import otus.student.kryukov.dz.domain.Author;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Author.class")
@DataJpaTest
@Import(AuthorDaoJpa.class)
public class AuthorDaoJpaTest {

    private final AuthorDao authorDao;
    private final TestEntityManager em;

    @Autowired
    public AuthorDaoJpaTest(AuthorDao authorDao, TestEntityManager em) {
        this.authorDao = authorDao;
        this.em = em;
    }

    @DisplayName("return Author-row from database by author-column")
    @Test
    void getByAuthorTest() {
        Author authorObject = authorDao.getByAuthor("James White").get();
        assertAll(
                () -> assertEquals("James White", authorObject.getAuthor()),
                () -> assertEquals(2L, authorObject.getAuthorId())
        );
        assertEquals(Optional.empty(), authorDao.getByAuthor("no such author exists"), "Error getting Optional.empty() by nonexistent author");
    }

    @DisplayName("return Author-row from database by author_id-column")
    @Test
    void getByAuthorIdTest() {
        Author authorObject = authorDao.getByAuthorId(2L).get();
        assertAll(
                () -> assertEquals("James White", authorObject.getAuthor()),
                () -> assertEquals(2L, authorObject.getAuthorId())
        );
        assertEquals(Optional.empty(), authorDao.getByAuthorId(-1L), "Error getting Optional.empty() by nonexistent author_id");
    }

    @DisplayName("insert new Author-row into table \"authors\" of database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createTest() {
        authorDao.create(new Author("Name Surname"));
        assertEquals("Name Surname", authorDao.getByAuthor("Name Surname").get().getAuthor(), "Error creating Author-row into table \"authors\" of database");
    }
}