package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.student.kryukov.dz.domain.Author;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AuthorRepository")
@DataJpaTest
public class AuthorRepositoryTest {

    private final AuthorRepository authorRepository;
    private final TestEntityManager em;

    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository, TestEntityManager em) {
        this.authorRepository = authorRepository;
        this.em = em;
    }

    @DisplayName("return Author-row from database by author-column")
    @Test
    void getByAuthorTest() {
        Author authorObject = authorRepository.findByAuthor("James White");
        assertAll(
                () -> assertEquals("James White", authorObject.getAuthor()),
                () -> assertEquals(2L, authorObject.getAuthorId())
        );
    }
}
