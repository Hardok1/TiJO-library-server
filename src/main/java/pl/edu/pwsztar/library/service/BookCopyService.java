package pl.edu.pwsztar.library.service;

public interface BookCopyService {

    Long getBookCopiesQuantity(String bookId);

    boolean addNewBookCopies(Long bookId, int quantity);
}
