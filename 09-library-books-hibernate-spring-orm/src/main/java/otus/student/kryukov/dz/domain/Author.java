package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author")
    private String author;

    public Author() {
    }

    public Author(String author) {
        this.author = author;
    }
}