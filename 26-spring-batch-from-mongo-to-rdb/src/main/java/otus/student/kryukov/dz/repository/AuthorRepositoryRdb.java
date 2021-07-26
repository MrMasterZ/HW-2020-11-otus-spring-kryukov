package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.student.kryukov.dz.domain.AuthorRdb;

public interface AuthorRepositoryRdb extends JpaRepository<AuthorRdb, Long> {

}