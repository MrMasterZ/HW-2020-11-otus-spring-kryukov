package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Genre;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getByGenre(String genre) {
        return Optional.ofNullable(genreRepository.findByGenre(genre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getByGenreId(String genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public void create(String genre) {
        checkEmptyGenre(genre);
        genreRepository.save(new Genre(genre));
    }

    @Override
    public void checkEmptyGenre(String genre) {
        if (genre == null || genre.trim().equals(""))
            throw new EmptyEntityInsertException("genre cannot be empty");
    }
}