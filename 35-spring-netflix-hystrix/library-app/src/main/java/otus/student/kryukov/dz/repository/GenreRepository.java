package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenre(@Param("genre") String genre);

}