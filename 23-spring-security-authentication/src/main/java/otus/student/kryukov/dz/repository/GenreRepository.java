package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findByGenre(@Param("genre") String genre);

}