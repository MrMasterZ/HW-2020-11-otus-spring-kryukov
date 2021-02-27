package otus.student.kryukov.dz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@NamedEntityGraph(name = "books-entity-graph", attributeNodes = {@NamedAttributeNode("bookObject")})
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
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