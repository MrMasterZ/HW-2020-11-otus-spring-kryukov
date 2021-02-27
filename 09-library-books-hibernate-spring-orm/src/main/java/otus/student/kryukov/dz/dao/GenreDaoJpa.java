package otus.student.kryukov.dz.dao;

import org.springframework.stereotype.Repository;
import otus.student.kryukov.dz.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genre> getByGenre(String genre) {
        Optional<Genre> optionalGenre;
        Query queryByGenre = em.createQuery("select g from Genre g where genre = :genre", Genre.class);
        queryByGenre.setParameter("genre", genre);
        try {
            optionalGenre = Optional.ofNullable((Genre) queryByGenre.getSingleResult());
        } catch (Exception e) {
            optionalGenre = Optional.empty();
        }
        return optionalGenre;
    }

    @Override
    public Optional<Genre> getByGenreId(Long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

    @Override
    public void create(Genre genreObject) {
        em.persist(genreObject);
    }

}