package otus.student.kryukov.dz.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String bookId;

    private String title;

    private Author authorObject;

    private Genre genreObject;

    public Book(String title, Author authorObject, Genre genreObject) {
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }
}