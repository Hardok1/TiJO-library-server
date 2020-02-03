package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Integer> getBookCopiesQuantity(@PathVariable("bookId") long bookId){
        try {
            return new ResponseEntity<>(bookCopyService.getBookCopiesQuantity(bookId).intValue(),HttpStatus.OK);
        } catch (InvalidBookException ibe){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
