package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookObject;

    public Comment() {
    }

    public Comment(String comment, Book bookObject) {
        this.comment = comment;
        this.bookObject = bookObject;
    }

    public Comment(Long commentId, String comment, Book bookObject) {
        this.commentId = commentId;
        this.comment = comment;
        this.bookObject = bookObject;
    }
}