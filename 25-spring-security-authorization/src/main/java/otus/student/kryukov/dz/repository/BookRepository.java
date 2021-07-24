package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    @EntityGraph(value = "authors-genres-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "authors-genres-entity-graph")
    List<Book> findByTitle(@Param("title") String title);

    List<Book> findByAuthorObject(@Param("authorObject") Author authorObject);

}