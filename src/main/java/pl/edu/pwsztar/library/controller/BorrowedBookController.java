package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.library.DTO.MyBooksDTO;
import pl.edu.pwsztar.library.DTO.RentBookDTO;
import pl.edu.pwsztar.library.DTO.ReturnBookDTO;
import pl.edu.pwsztar.library.service.BorrowedBookService;

import java.util.List;

@RestController
@RequestMapping("/borrowing/")
public class BorrowedBookController {

    final BorrowedBookService borrowedBookService;

    @Autowired
    public BorrowedBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping("/myBooks/{userId}")
    public ResponseEntity<List<MyBooksDTO>> getAccountBorrowedBooks(@PathVariable("userId") long userId){

        return new ResponseEntity<>(borrowedBookService.getAccountBorrowedBooks(userId), HttpStatus.OK);
    }

    @PostMapping("rentBook")
    public ResponseEntity<String> rentBook(@RequestBody RentBookDTO rentBookDTO){
        if (borrowedBookService.borrowBook(rentBookDTO.getAccountId(),rentBookDTO.getBookId())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("returnBook")
    public ResponseEntity<String> returnBorrowedBook(@RequestBody ReturnBookDTO returnBookDTO){
        if (borrowedBookService.returnBook(returnBookDTO.getAccountId(),returnBookDTO.getBookCopyId())){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
