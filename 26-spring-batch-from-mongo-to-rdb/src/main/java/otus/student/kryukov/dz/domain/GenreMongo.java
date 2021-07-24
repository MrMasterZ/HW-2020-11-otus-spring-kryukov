package otus.student.kryukov.dz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
public class GenreMongo {

    @Id
    private String genreId;

    private String genre;

    public GenreMongo(String genre) {
        this.genre = genre;
    }
}