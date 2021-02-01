package otus.student.kryukov.dz.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;
import otus.student.kryukov.dz.domain.Genre;

@RequiredArgsConstructor
@Data
public class BookDto {

    private Book bookObject;
    private Author authorObject;
    private Genre genreObject;

    public BookDto(Book bookObject, Author authorObject, Genre genreObject) {
        this.bookObject = bookObject;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }
}
