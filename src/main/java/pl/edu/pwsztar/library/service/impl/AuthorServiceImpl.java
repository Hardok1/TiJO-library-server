package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.InvalidAuthorException;
import pl.edu.pwsztar.library.model.Author;
import pl.edu.pwsztar.library.repository.AuthorRepository;
import pl.edu.pwsztar.library.service.AccountService;
import pl.edu.pwsztar.library.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository authorRepository;
    final AccountService accountService;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AccountService accountService) {
        this.authorRepository = authorRepository;
        this.accountService = accountService;
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
    public boolean addAuthor(String authorName, Long accountId) {
        if (accountService.isAdmin(accountId)){
            Author author = new Author();
            author.setAuthorName(authorName);
            Author a = authorRepository.save(author);
            return true;
        }
        return false;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


}
