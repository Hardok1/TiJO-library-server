package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.InvalidBookException;
import pl.edu.pwsztar.library.model.Author;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.repository.AuthorRepository;
import pl.edu.pwsztar.library.repository.BookCopyRepository;
import pl.edu.pwsztar.library.repository.BookRepository;
import pl.edu.pwsztar.library.service.AccountService;
import pl.edu.pwsztar.library.service.BookCopyService;
import pl.edu.pwsztar.library.service.BookService;
import pl.edu.pwsztar.library.service.ReviewService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final ReviewService reviewService;
    final AuthorRepository authorRepository;
    final BookCopyService bookCopyService;
    private AccountService accountService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReviewService reviewService, AuthorRepository authorRepository, BookCopyRepository bookCopyRepository, BookCopyService bookCopyService) {
        this.bookRepository = bookRepository;
        this.reviewService = reviewService;
        this.authorRepository = authorRepository;
        this.bookCopyService = bookCopyService;
    }

    @Override
    public Book getBook(Long id) throws InvalidBookException{
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            return book.get();
        }
        throw new InvalidBookException();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getTop10Books() {
//        List <Book> books = getAllBooks();
//
//        for (Book book : books){
//
//        }
        return null;
    }

    @Override
    public boolean addBook(String name, String description, String bookImageUrl, Double price, List<Long> authorsId, int quantity, Long accountId) {
        if (accountService.isAdmin(accountId)) {
            List<Author> authors = authorRepository.findAllById(authorsId);
            if (authors.size() > 0) {
                Book book = bookRepository.save(new Book(name, description, bookImageUrl, price, authorRepository.findAllById(authorsId)));
                bookCopyService.addNewBookCopies(book.getId(), quantity, accountId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editBook(Long bookId, String name, String description, String bookImageUrl, Double price, List<Long> authorsId, Long accountId) {
        if (accountService.isAdmin(accountId)) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isPresent()) {
                book.get().setName(name);
                book.get().setDescription(description);
                book.get().setBookImageUrl(bookImageUrl);
                book.get().setPrice(price);
                book.get().setAuthor(authorRepository.findAllById(authorsId));
                bookRepository.save(book.get());
                return true;
            }
        }
        return false;
    }
}
