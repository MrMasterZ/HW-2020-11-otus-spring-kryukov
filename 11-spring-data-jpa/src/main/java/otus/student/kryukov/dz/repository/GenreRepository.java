package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select g from Genre g where g.genre = :genre")
    Genre findByGenre(@Param("genre") String genre);

}