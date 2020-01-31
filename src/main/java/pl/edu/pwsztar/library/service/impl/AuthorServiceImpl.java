package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.InvalidAuthorException;
import pl.edu.pwsztar.library.model.Author;
import pl.edu.pwsztar.library.repository.AuthorRepository;
import pl.edu.pwsztar.library.service.AuthorService;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public String getAuthorName(Long id) throws InvalidAuthorException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author.get().getAuthorName();
        }
        throw new InvalidAuthorException();
    }

    @Override
    public boolean addAuthor(String authorName) {
        Author author = new Author();
        author.setAuthorName(authorName);
        authorRepository.save(author);
        return true;
    }

}
