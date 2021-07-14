package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import otus.student.kryukov.dz.domain.Genre;
import reactor.core.publisher.Mono;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

    Mono<Genre> findByGenre(@Param("genre") String genre);

}