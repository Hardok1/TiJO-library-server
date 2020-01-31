package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.model.Book;

import java.util.List;

public interface BookService {

    Book getBook(Long id);

    List<Book> getAllBooks();

    List<Book> getTop10Books();

    boolean addBook(String name, String description, String bookImageUrl, Double price, List<Long> authorId, int quantity);

    boolean editBook(Long bookId, String name, String description, String bookImageUrl, Double price, List<Long> authorsId);
}
