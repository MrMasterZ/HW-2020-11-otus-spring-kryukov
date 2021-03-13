package otus.student.kryukov.dz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.student.kryukov.dz.dao.AuthorDao;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.print.PrintService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("AuthorServiceImpl.class")
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    private AuthorService authorService;

    @Mock
    private AuthorDao authorDao;

    @Mock
    private PrintService printService;

    @BeforeEach
    private void init() {
        authorService = new AuthorServiceImpl(authorDao);
    }

    @DisplayName("return Author-row from database by author-column")
    @Test
    void getByAuthorTest() {
        Author expected = new Author("test author");
        when(authorDao.getByAuthor("test author")).thenReturn(Optional.of(expected));
        Author actual = authorService.getByAuthor("test author").get();
        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(authorDao, times(1)).getByAuthor("test author")
        );
        assertEquals(Optional.empty(), authorService.getByAuthor("no such author exists"), "Error getting Optional.empty() by nonexistent author");
    }

    @DisplayName("return Author-row from database by author_id-column")
    @Test
    void getByAuthorIdTest() {
        Author expected = new Author("test author");
        when(authorDao.getByAuthorId(1L)).thenReturn(Optional.of(expected));
        Author actual = authorService.getByAuthorId(1L).get();
        assertAll(
                () -> assertEquals(expected, actual),
                () -> verify(authorDao, times(1)).getByAuthorId(1L)
        );
        assertEquals(Optional.empty(), authorService.getByAuthorId(-1L), "Error getting Optional.empty() by nonexistent author_id");
    }
}