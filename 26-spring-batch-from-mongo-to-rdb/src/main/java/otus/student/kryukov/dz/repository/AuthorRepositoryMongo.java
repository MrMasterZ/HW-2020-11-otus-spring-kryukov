package otus.student.kryukov.dz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.student.kryukov.dz.domain.AuthorMongo;

public interface AuthorRepositoryMongo extends MongoRepository<AuthorMongo, String> {

}