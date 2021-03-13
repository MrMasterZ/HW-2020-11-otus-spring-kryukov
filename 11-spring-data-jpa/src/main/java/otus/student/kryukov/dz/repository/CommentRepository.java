package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.bookObject b join fetch b.authorObject join fetch b.genreObject")
    List<Comment> findAllFetch();

   @Query("select c from Comment c join fetch c.bookObject b join fetch b.authorObject join fetch b.genreObject where c.comment = :comment")
   List<Comment> findByComment(@Param("comment") String comment);

}