package pl.edu.pwsztar.library.service;

public interface BookCopyService {

    Long getBookCopiesQuantity(Long bookId);

    boolean addNewBookCopies(Long bookId, int quantity, Long accountId);
}
