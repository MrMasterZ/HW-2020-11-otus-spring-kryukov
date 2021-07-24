package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class BookRdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorRdb authorObject;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreRdb genreObject;

    public BookRdb() {
    }

    public BookRdb(String title, AuthorRdb authorObject, GenreRdb genreObject) {
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }

    public BookRdb(Long bookId, String title, AuthorRdb authorObject, GenreRdb genreObject) {
        this.bookId = bookId;
        this.title = title;
        this.authorObject = authorObject;
        this.genreObject = genreObject;
    }
}