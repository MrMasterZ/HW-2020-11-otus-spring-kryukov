package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genres")
public class GenreRdb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "genre")
    private String genre;

    public GenreRdb() {
    }

    public GenreRdb(Long genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }
}