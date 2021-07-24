package otus.student.kryukov.dz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookMongo {

    @Id
    private String bookId;

    private String title;

    @DBRef
    private AuthorMongo authorObject;

    @DBRef
    private GenreMongo genreObject;

    public BookMongo(String title, AuthorMongo authorObject, GenreMongo genreObject) {
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }
}