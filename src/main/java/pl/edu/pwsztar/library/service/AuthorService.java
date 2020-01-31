package pl.edu.pwsztar.library.service;

public interface AuthorService {

    String getAuthorName(Long id);

    boolean addAuthor(String authorName);
}
