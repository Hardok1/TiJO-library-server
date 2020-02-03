package pl.edu.pwsztar.library.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.library.DTO.BookAddDTO;
import pl.edu.pwsztar.library.DTO.BookDTO;
import pl.edu.pwsztar.library.DTO.BookEditDTO;
import pl.edu.pwsztar.library.DTO.MyBooksDTO;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.service.BookService;
import pl.edu.pwsztar.library.service.BorrowedBookService;
import pl.edu.pwsztar.library.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books/")
public class BookController {

    final BookService bookService;
    final ReviewService reviewService;
    final BorrowedBookService borrowedBookService;

    @Autowired
    public BookController(BookService bookService, ReviewService reviewService, BorrowedBookService borrowedBookService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.borrowedBookService = borrowedBookService;
    }

    @PostMapping("addBook")
    public ResponseEntity<String> addBook(@RequestBody BookAddDTO bookAddDTO) {
        if (bookService.addBook(bookAddDTO.getName(), bookAddDTO.getDescription(), bookAddDTO.getBookImageUrl(),
                bookAddDTO.getPrice(), bookAddDTO.getAuthors(), bookAddDTO.getQuantity(), bookAddDTO.getAccountId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("editBook")
    public ResponseEntity<String> editBook(@RequestBody BookEditDTO bookEditDTO) {
        if (bookService.editBook(bookEditDTO.getBookId(), bookEditDTO.getName(), bookEditDTO.getDescription(),
                bookEditDTO.getBookImageUrl(), bookEditDTO.getPrice(), bookEditDTO.getAuthors(), bookEditDTO.getAccountId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<BookDTO>> getBooksList() {
        return getBookDTOList(bookService.getAllBooks());
    }

    @GetMapping("getBookById/{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("bookId") long bookId) {
        try {
            Book book = bookService.getBook(bookId);
            BookDTO bookDTO = new BookDTO(book.getId(), book.getName(), book.getBookImageUrl(), book.getDescription(),
                    book.getPrice(), reviewService.getAverageGradeForBook(bookId), book.getAuthor());
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("getTop")
    public ResponseEntity<List<BookDTO>> getTop() {
        return getBookDTOList(bookService.getTop10Books());
    }

    @GetMapping("/myBooks/{userId}")
    public ResponseEntity<List<MyBooksDTO>> getAccountBorrowedBooks(@PathVariable("userId") long userId){

        return new ResponseEntity<>(borrowedBookService.getAccountBorrowedBooks(userId),HttpStatus.OK);
    }

    @NotNull
    private ResponseEntity<List<BookDTO>> getBookDTOList(List<Book> books) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : books) {
            bookDTOList.add(new BookDTO(book.getId(), book.getName(), book.getBookImageUrl(), book.getDescription(),
                    book.getPrice(), reviewService.getAverageGradeForBook(book.getId()), book.getAuthor()));
        }
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }
}
