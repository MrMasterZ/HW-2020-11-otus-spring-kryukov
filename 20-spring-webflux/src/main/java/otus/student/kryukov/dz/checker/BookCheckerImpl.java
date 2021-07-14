package otus.student.kryukov.dz.checker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;

@Service
@RequiredArgsConstructor
public class BookCheckerImpl implements BookChecker {
    @Override
    public void checkEmpty(String title, String author, String genre) {
        checkEmptyTitle(title);
        checkEmptyAuthor(author);
        checkEmptyGenre(genre);
    }

    private void checkEmptyTitle(String title) {
        if (title == null || title.trim().equals(""))
            throw new EmptyEntityInsertException("title cannot be empty");
    }

    private void checkEmptyAuthor(String author) {
        if (author == null || author.trim().equals(""))
            throw new EmptyEntityInsertException("author cannot be empty");
    }

    private void checkEmptyGenre(String genre) {
        if (genre == null || genre.trim().equals(""))
            throw new EmptyEntityInsertException("genre cannot be empty");
    }
}