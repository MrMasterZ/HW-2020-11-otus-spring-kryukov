package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("DAO for Author.class")
@JdbcTest
@Import(AuthorDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorDaoJdbcTest {
    private final AuthorDao authorDao;

    @Autowired
    public AuthorDaoJdbcTest(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @DisplayName("return Author-row from database by author-column")
    @Test
    void getByAuthorTest() {
        Author author = new Author(2L, "James White");
        assertEquals(author, authorDao.getByAuthor("James White"), "Error getting Author-row by author");
        assertNull(authorDao.getByAuthor("sedfghjkmngfcvgbhjhgdesdft"), "Error getting null by nonexistent author");
    }

    @DisplayName("return Author-row from database by author_id-column")
    @Test
    void getByAuthorIdTest() {
        Author author = new Author(2L, "James White");
        assertEquals(author, authorDao.getByAuthorId(2L), "Error getting Author-row by author_id");
        assertNull(authorDao.getByAuthorId(-1L), "Error getting null by nonexistent author_id");
    }

    @DisplayName("insert new Author-row into table \"authors\" of database and return his author_id")
    @DirtiesContext(methodMode = AFTER_METHOD)
    @Test
    void createByAuthorTest() {
        String author = "Name Surname";
        long authorId = authorDao.createByAuthor(author);
        Author authorObjectExpected = new Author(authorId, author);
        Author authorObjectGet = authorDao.getByAuthorId(authorId);
        assertEquals(authorObjectExpected, authorObjectGet, "Error creating Author-row");
    }
}