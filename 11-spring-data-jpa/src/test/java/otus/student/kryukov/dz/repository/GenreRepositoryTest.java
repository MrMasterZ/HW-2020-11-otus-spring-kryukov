package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import otus.student.kryukov.dz.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GenreRepository")
@DataJpaTest
public class GenreRepositoryTest {
    private final GenreRepository genreRepository;
    private final TestEntityManager em;

    @Autowired
    public GenreRepositoryTest(GenreRepository genreRepository, TestEntityManager em) {
        this.genreRepository = genreRepository;
        this.em = em;
    }

    @DisplayName("return Genre-row from database by genre-column")
    @Test
    void getByGenreTest() {
        Genre genreObject = genreRepository.findByGenre("poem");
        assertAll(
                () -> assertEquals("poem", genreObject.getGenre()),
                () -> assertEquals(3L, genreObject.getGenreId())
        );
    }
}