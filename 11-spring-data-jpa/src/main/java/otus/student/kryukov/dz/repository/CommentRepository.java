package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"bookObject", "bookObject.authorObject", "bookObject.genreObject"})
    List<Comment> findAll();

    @EntityGraph(attributePaths = {"bookObject", "bookObject.authorObject", "bookObject.genreObject"})
    List<Comment> findByComment(@Param("comment") String comment);

}