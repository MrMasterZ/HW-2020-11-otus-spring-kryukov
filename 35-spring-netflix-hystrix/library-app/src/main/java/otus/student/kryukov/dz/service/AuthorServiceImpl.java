package otus.student.kryukov.dz.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.exception.ServerException;
import otus.student.kryukov.dz.repository.AuthorRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private Map<Long, Author> cache = new HashMap<>();

    @Override
    @HystrixCommand(commandKey = "getAuthors", fallbackMethod = "getByAuthorFromCache")
    public Optional<Author> getByAuthor(String author) {
        return Optional.ofNullable(authorRepository.findByAuthor(author));
    }

    @Override
    @HystrixCommand(commandKey = "getAuthors", fallbackMethod = "getByAuthorIdFromCache")
    public Optional<Author> getByAuthorId(Long authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    @HystrixCommand(commandKey = "getAuthors", fallbackMethod = "getAuthorsFromCache")
    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        updateCache(authors);
        return authors;
    }

    @Override
    @HystrixCommand(commandKey="updateAuthors", fallbackMethod = "throwException")
    public void create(String author) {
        checkEmptyAuthor(author);
        authorRepository.save(new Author(author));
    }

    @Override
    @HystrixCommand(commandKey="updateAuthors", fallbackMethod = "throwException")
    public void delete(String author) {
        authorRepository.deleteById(
                getByAuthor(author).get().getAuthorId());
    }

    @Override
    public void checkEmptyAuthor(String author) {
        if (author == null || author.trim().equals(""))
            throw new EmptyEntityInsertException("author cannot be empty");
    }

    private Optional<Author> getByAuthorFromCache(String author) {
        for (Author authorObject : cache.values()) {
            if (authorObject.getAuthor().equals(author))
                return Optional.of(authorObject);
        }
        return Optional.empty();
    }

    private Optional<Author> getByAuthorIdFromCache(Long authorId) {
        return Optional.ofNullable(cache.get(authorId));
    }

    private List<Author> getAuthorsFromCache() {
        return new ArrayList<>(cache.values());
    }

    private void throwException(String author) {
        throw new ServerException("Temporary server problems, try a little later, please");
    }

    private void updateCache(List<Author> authors) {
        cache = authors.stream().collect(Collectors.toMap(
                Author::getAuthorId, author -> author
        ));
    }
}