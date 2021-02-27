package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long bookId;
    private final String title;
    private final long authorId;
    private final long genreId;
}
