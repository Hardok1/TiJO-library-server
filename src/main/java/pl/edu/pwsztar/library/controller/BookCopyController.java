package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.library.DTO.CopiesAddDTO;
import pl.edu.pwsztar.library.exception.InvalidBookException;
import pl.edu.pwsztar.library.service.BookCopyService;

@RestController
@RequestMapping("/bookCopies/")
public class BookCopyController {

    private final BookCopyService bookCopyService;

    @Autowired
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @GetMapping("quantity/{bookId}")
    public ResponseEntity<Long> getNotBorrowedBookCopiesLeftQuantity(@PathVariable("bookId") long bookId) {
        try {
            Long bookCopiesCount = bookCopyService.getBookCopiesQuantity(bookId);
            return new ResponseEntity<>(bookCopiesCount, HttpStatus.OK);
        } catch (InvalidBookException ibe) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("addCopies")
    public ResponseEntity<String> addCopies(@RequestBody CopiesAddDTO copiesAddDTO) {
        if (bookCopyService.addNewBookCopies(copiesAddDTO.getBookId(), copiesAddDTO.getQuantity(), copiesAddDTO.getAccountId())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}