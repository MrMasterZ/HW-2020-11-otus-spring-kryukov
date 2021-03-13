package otus.student.kryukov.dz.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorObject;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genreObject;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, mappedBy = "bookObject")
    private List<Comment> comments;

    public Book() {
    }

    public Book(String title, Author authorObject, Genre genreObject) {
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }

    public Book(Long bookId, String title, Author authorObject, Genre genreObject) {
        this.bookId = bookId;
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }
}