package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.InvalidBookException;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.BookCopy;
import pl.edu.pwsztar.library.repository.BookCopyRepository;
import pl.edu.pwsztar.library.repository.BookRepository;
import pl.edu.pwsztar.library.service.BookCopyService;

import java.util.Optional;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    final BookCopyRepository bookCopyRepository;
    final BookRepository bookRepository;

    @Autowired
    public BookCopyServiceImpl(BookCopyRepository bookCopyRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Long getBookCopiesQuantity(String bookId) throws InvalidBookException {
        Optional<Book> book = bookRepository.findById(Long.valueOf(bookId));
        if (book.isPresent()){
            return bookCopyRepository.countByBook(book.get());
        }
        throw new InvalidBookException();
    }

    @Override
    public boolean addNewBookCopies(Long bookId, int quantity) throws InvalidBookException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()){
            BookCopy bookCopy = new BookCopy();
            bookCopy.setBook(book.get());
            bookCopy.setBorrowed(false);
            bookCopyRepository.save(bookCopy);
        } else {
            throw new InvalidBookException();
        }
        return true;
    }
}
