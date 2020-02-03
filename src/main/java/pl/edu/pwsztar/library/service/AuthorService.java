package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.model.Author;

import java.util.List;

public interface AuthorService {

    String getAuthorName(Long id);

    boolean addAuthor(String authorName, Long accountId);

    List<Author> getAllAuthors();
}
