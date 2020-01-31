package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.BookAlreadyBorrowedException;
import pl.edu.pwsztar.library.exception.InvalidAccountException;
import pl.edu.pwsztar.library.exception.InvalidBookException;
import pl.edu.pwsztar.library.exception.NoBooksCopiesLeftException;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.BookCopy;
import pl.edu.pwsztar.library.model.BorrowedBook;
import pl.edu.pwsztar.library.repository.AccountRepository;
import pl.edu.pwsztar.library.repository.BookCopyRepository;
import pl.edu.pwsztar.library.repository.BookRepository;
import pl.edu.pwsztar.library.repository.BorrowedBookRepository;
import pl.edu.pwsztar.library.service.BorrowedBookService;

import java.util.*;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

    final BorrowedBookRepository borrowedBookRepository;
    final AccountRepository accountRepository;
    final BookRepository bookRepository;
    final BookCopyRepository bookCopyRepository;

    @Autowired
    public BorrowedBookServiceImpl(BorrowedBookRepository borrowedBookRepository, AccountRepository accountRepository, BookRepository bookRepository, BookCopyRepository bookCopyRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    @Override
    public List<Book> getAccountBorrowedBooks(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        List<Book> books = new ArrayList<>();
        if (account.isPresent()) {
            List<BorrowedBook> borrowedBooks = borrowedBookRepository.findAllByAccount(account.get());
            borrowedBooks.forEach(borrowedBook -> books.add(borrowedBook.getBookCopy().getBook()));
        }
        return books;
    }

    @Override
    public boolean borrowBook(Long accountId, Long bookId) throws InvalidAccountException, InvalidBookException, NoBooksCopiesLeftException, BookAlreadyBorrowedException {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isPresent()) {
                if (!isBookAlreadyBorrowed(accountId, bookId)) {
                    Optional<BookCopy> bookCopy = bookCopyRepository.findFirstByBookAndBorrowed(book.get(), false);
                    if (bookCopy.isPresent()) {
                        BorrowedBook borrowedBook = new BorrowedBook();
                        borrowedBook.setAccount(account.get());
                        borrowedBook.setBookCopy(bookCopy.get());
                        borrowedBook.setBorrowDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
                        borrowedBookRepository.save(borrowedBook);
                        bookCopy.get().setBorrowed(true);
                        bookCopyRepository.save(bookCopy.get());
                    } else {
                        throw new NoBooksCopiesLeftException();
                    }
                } else {
                    throw new BookAlreadyBorrowedException();
                }
            } else {
                throw new InvalidBookException();
            }
        } else {
            throw new InvalidAccountException();
        }
        return false;
    }
//14 o 14
    @Override
    public boolean returnBook(Long accountId, Long bookCopyId) throws InvalidAccountException {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookCopyId);
            if (bookCopy.isPresent()) {
                for (BorrowedBook borrowedBook : borrowedBookRepository.findAllByAccount(account.get())) {
                    if (borrowedBook.getBookCopy().equals(bookCopy.get())) {
                        borrowedBook.setReturnDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
                        borrowedBookRepository.save(borrowedBook);
                        bookCopy.get().setBorrowed(false);
                        bookCopyRepository.save(bookCopy.get());
                        return true;
                    }
                }
            }
        } else {
            throw new InvalidAccountException();
        }
        return false;
    }

    @Override
    public boolean isBookAlreadyBorrowed(Long accountId, Long bookId) {
        for (Book borrowedBook : getAccountBorrowedBooks(accountId)) {
            if (borrowedBook.getId().equals(bookId)) {
                return true;
            }
        }
        return false;
    }
}