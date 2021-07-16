package otus.student.kryukov.dz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.student.kryukov.dz.domain.Author;
import otus.student.kryukov.dz.exception.EmptyEntityInsertException;
import otus.student.kryukov.dz.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getByAuthor(String author) {
        return Optional.ofNullable(authorRepository.findByAuthor(author));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getByAuthorId(String authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void create(String author) {
        checkEmptyAuthor(author);
        authorRepository.save(new Author(author));
    }

    @Override
    @Transactional
    public void delete(String author) {
        authorRepository.deleteById(
                getByAuthor(author).get().getAuthorId());
    }

    @Override
    public void checkEmptyAuthor(String author) {
        if (author == null || author.trim().equals(""))
            throw new EmptyEntityInsertException("author cannot be empty");
    }

}