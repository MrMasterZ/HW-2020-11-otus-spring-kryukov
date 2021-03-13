package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

   @Query("select b from Book b join fetch b.authorObject join fetch b.genreObject")
    List<Book> findAllFetch();

    @Query("select b from Book b join fetch b.authorObject join fetch b.genreObject where b.title = :title")
    List<Book> findByTitle(@Param("title") String title);

}