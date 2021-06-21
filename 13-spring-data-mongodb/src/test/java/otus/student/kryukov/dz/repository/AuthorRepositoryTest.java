package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import otus.student.kryukov.dz.domain.Author;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AuthorRepository")
@DataMongoTest
public class AuthorRepositoryTest {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @DisplayName("return Author-row from database by author-column")
    @Test
    void findByAuthorTest() {
        Author authorObject = authorRepository.findByAuthor("James White");
        assertAll(
                () -> assertEquals("James White", authorObject.getAuthor()),
                () -> assertEquals("2", authorObject.getAuthorId())
        );
    }
}