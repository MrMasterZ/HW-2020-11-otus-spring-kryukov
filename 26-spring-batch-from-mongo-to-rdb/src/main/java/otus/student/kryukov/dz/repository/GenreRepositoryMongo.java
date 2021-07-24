package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.student.kryukov.dz.domain.GenreMongo;

public interface GenreRepositoryMongo extends MongoRepository<GenreMongo, String> {

}