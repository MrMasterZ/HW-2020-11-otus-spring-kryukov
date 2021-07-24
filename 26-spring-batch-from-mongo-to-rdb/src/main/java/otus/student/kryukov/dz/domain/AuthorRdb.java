package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class AuthorRdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author")
    private String author;

    public AuthorRdb() {
    }

    public AuthorRdb(Long authorId, String author) {
        this.authorId = authorId;
        this.author = author;
    }
}