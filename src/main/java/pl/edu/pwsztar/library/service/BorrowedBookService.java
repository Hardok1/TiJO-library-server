package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.model.Book;

import java.util.List;

public interface BorrowedBookService {

    List<Book> getAccountBorrowedBooks(Long accountId);

    boolean borrowBook(Long accountId, Long bookId);

    boolean returnBook(Long accountId, Long bookId);

    boolean isBookAlreadyBorrowed(Long accountId, Long bookId);
}
