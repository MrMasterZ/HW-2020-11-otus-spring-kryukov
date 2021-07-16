package otus.student.kryukov.dz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import otus.student.kryukov.dz.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GenreRepository")
@DataMongoTest
public class GenreRepositoryTest {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreRepositoryTest(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @DisplayName("return Genre-row from database by genre-column")
    @Test
    void findByGenreTest() {
        Genre genreObject = genreRepository.findByGenre("fantasy");
        assertAll(
                () -> assertEquals("fantasy", genreObject.getGenre()),
                () -> assertEquals("1", genreObject.getGenreId())
        );
    }
}