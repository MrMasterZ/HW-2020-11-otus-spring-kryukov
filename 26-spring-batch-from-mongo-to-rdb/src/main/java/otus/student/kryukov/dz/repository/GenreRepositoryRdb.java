package otus.student.kryukov.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.student.kryukov.dz.domain.GenreRdb;

public interface GenreRepositoryRdb extends JpaRepository<GenreRdb, Long> {

}