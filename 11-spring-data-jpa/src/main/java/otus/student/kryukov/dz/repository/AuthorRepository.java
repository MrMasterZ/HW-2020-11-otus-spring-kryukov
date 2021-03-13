package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.author = :author")
    Author findByAuthor(@Param("author") String author);

}