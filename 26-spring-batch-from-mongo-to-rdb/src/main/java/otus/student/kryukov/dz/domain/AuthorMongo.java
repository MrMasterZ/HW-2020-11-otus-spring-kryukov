package otus.student.kryukov.dz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class AuthorMongo {

    @Id
    private String authorId;

    private String author;

    public AuthorMongo(String author) {
        this.author = author;
    }
}