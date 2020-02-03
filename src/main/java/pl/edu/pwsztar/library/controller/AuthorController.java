package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.library.DTO.AddAuthorDTO;
import pl.edu.pwsztar.library.DTO.AuthorNameDTO;
import pl.edu.pwsztar.library.exception.InvalidAuthorException;
import pl.edu.pwsztar.library.model.Author;
import pl.edu.pwsztar.library.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors/")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody AddAuthorDTO addAuthorDTO){
        if (authorService.addAuthor(addAuthorDTO.getAuthorName(), addAuthorDTO.getAccountId())){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("getAuthorName/{authorId}")
    public ResponseEntity<AuthorNameDTO> getAuthorName(@PathVariable("authorId") long authorId){
        try {
            return new ResponseEntity<>(new AuthorNameDTO(authorService.getAuthorName(authorId)), HttpStatus.OK);
        } catch (InvalidAuthorException iae){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("getAuthors")
    public ResponseEntity<List<Author>> getAuthorsList(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }


}
