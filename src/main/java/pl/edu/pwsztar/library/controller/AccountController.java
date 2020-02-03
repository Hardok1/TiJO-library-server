package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwsztar.library.DTO.AccountCredentialsDTO;
import pl.edu.pwsztar.library.DTO.AccountDTO;
import pl.edu.pwsztar.library.exception.InvalidCredentialsException;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.service.AccountService;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody AccountDTO accountDTO) {
        if (accountService.createAccount(new Account(accountDTO))) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("signin")
    public ResponseEntity<Account> signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        try {
            Account account = accountService.logIn(accountCredentialsDTO);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (InvalidCredentialsException ice) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
