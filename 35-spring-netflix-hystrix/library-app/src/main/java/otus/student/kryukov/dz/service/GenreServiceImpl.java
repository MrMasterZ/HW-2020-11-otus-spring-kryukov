package otus.student.kryukov.dz.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.ServerException;
import otus.student.kryukov.dz.repository.GenreRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private Map<Long, Genre> cache = new HashMap<>();

    @Override
    @HystrixCommand(commandKey = "getGenres", fallbackMethod = "getByGenreFromCache")
    public Optional<Genre> getByGenre(String genre) {
        return Optional.ofNullable(genreRepository.findByGenre(genre));
    }

    @Override
    @HystrixCommand(commandKey = "getGenres", fallbackMethod = "getByGenreIdFromCache")
    public Optional<Genre> getByGenreId(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    @HystrixCommand(commandKey = "getGenres", fallbackMethod = "getGenresFromCache")
    public List<Genre> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        updateCache(genres);
        return genres;
    }

    @Override
    @HystrixCommand(commandKey="updateGenres", fallbackMethod = "throwException")
    public void create(String genre) {
        checkEmptyGenre(genre);
        genreRepository.save(new Genre(genre));
    }

    @Override
    public void checkEmptyGenre(String genre) {
        if (genre == null || genre.trim().equals(""))
            throw new EmptyEntityInsertException("genre cannot be empty");
    }

    private Optional<Genre> getByGenreFromCache(String genre) {
        for (Genre genreObject : cache.values()) {
            if (genreObject.getGenre().equals(genre))
                return Optional.of(genreObject);
        }
        return Optional.empty();
    }

    Optional<Genre> getByGenreIdFromCache(Long genreId) {
        return Optional.ofNullable(cache.get(genreId));
    }

    private List<Genre> getGenresFromCache() {
        return new ArrayList<>(cache.values());
    }

    private void throwException(String genre) {
        throw new ServerException("Temporary server problems, try a little later, please");
    }

    private void updateCache(List<Genre> genres) {
        cache = genres.stream().collect(Collectors.toMap(
                Genre::getGenreId, genre -> genre
        ));
    }
}