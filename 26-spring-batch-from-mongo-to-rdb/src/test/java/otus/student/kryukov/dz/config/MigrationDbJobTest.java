package otus.student.kryukov.dz.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otus.student.kryukov.dz.domain.AuthorRdb;
import otus.student.kryukov.dz.domain.BookRdb;
import otus.student.kryukov.dz.domain.GenreRdb;
import otus.student.kryukov.dz.repository.AuthorRepositoryRdb;
import otus.student.kryukov.dz.repository.BookRepositoryRdb;
import otus.student.kryukov.dz.repository.GenreRepositoryRdb;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static otus.student.kryukov.dz.config.JobConfig.*;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
class MigrationDbJobTest {

    private final JobLauncherTestUtils jobLauncherTestUtils;
    private final JobRepositoryTestUtils jobRepositoryTestUtils;
    private final BookRepositoryRdb bookRepositoryRdb;
    private final AuthorRepositoryRdb authorRepositoryRdb;
    private final GenreRepositoryRdb genreRepositoryRdb;

    @Autowired
    public MigrationDbJobTest(JobLauncherTestUtils jobLauncherTestUtils, JobRepositoryTestUtils jobRepositoryTestUtils,
                              BookRepositoryRdb bookRepositoryRdb, AuthorRepositoryRdb authorRepositoryRdb,
                              GenreRepositoryRdb genreRepositoryRdb) {
        this.jobLauncherTestUtils = jobLauncherTestUtils;
        this.jobRepositoryTestUtils = jobRepositoryTestUtils;
        this.bookRepositoryRdb = bookRepositoryRdb;
        this.authorRepositoryRdb = authorRepositoryRdb;
        this.genreRepositoryRdb = genreRepositoryRdb;
    }

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    @DisplayName("job reads from Mongo (DatabaseChangelog) and writes to RDB")
    void testJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(MIGRATION_DB_JOB_NAME);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<BookRdb> books = bookRepositoryRdb.findAll();

        assertThat(books).hasSize(4);
        assertAll(
                () -> assertEquals(1L, books.get(0).getBookId()),
                () -> assertEquals("Prelude to the foundation", books.get(0).getTitle()),
                () -> assertEquals(1L, books.get(0).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(0).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(0).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(0).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(2L, books.get(1).getBookId()),
                () -> assertEquals("Martian path", books.get(1).getTitle()),
                () -> assertEquals(1L, books.get(1).getAuthorObject().getAuthorId()),
                () -> assertEquals("Isaac Asimov", books.get(1).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(1).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(1).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(3L, books.get(2).getBookId()),
                () -> assertEquals("Space hospital", books.get(2).getTitle()),
                () -> assertEquals(2L, books.get(2).getAuthorObject().getAuthorId()),
                () -> assertEquals("James White", books.get(2).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(2).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(2).getGenreObject().getGenre())
        );
        assertAll(
                () -> assertEquals(4L, books.get(3).getBookId()),
                () -> assertEquals("Seeded moon", books.get(3).getTitle()),
                () -> assertEquals(3L, books.get(3).getAuthorObject().getAuthorId()),
                () -> assertEquals("Connie Willis", books.get(3).getAuthorObject().getAuthor()),
                () -> assertEquals(1L, books.get(3).getGenreObject().getGenreId()),
                () -> assertEquals("fantasy", books.get(3).getGenreObject().getGenre())
        );

        List<AuthorRdb> authors = authorRepositoryRdb.findAll();

        assertThat(authors).hasSize(4);
        assertAll(
                () -> assertEquals("Isaac Asimov", authors.get(0).getAuthor()),
                () -> assertEquals(1L, authors.get(0).getAuthorId())
        );
        assertAll(
                () -> assertEquals("James White", authors.get(1).getAuthor()),
                () -> assertEquals(2L, authors.get(1).getAuthorId())
        );
        assertAll(
                () -> assertEquals("Connie Willis", authors.get(2).getAuthor()),
                () -> assertEquals(3L, authors.get(2).getAuthorId())
        );
        assertAll(
                () -> assertEquals("Test", authors.get(3).getAuthor()),
                () -> assertEquals(4L, authors.get(3).getAuthorId())
        );

        List<GenreRdb> genres = genreRepositoryRdb.findAll();
        assertThat(genres).hasSize(2);
        assertAll(
                () -> assertEquals("fantasy", genres.get(0).getGenre()),
                () -> assertEquals(1L, genres.get(0).getGenreId())
        );
        assertAll(
                () -> assertEquals("test", genres.get(1).getGenre()),
                () -> assertEquals(2L, genres.get(1).getGenreId())
        );
    }
}