package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.exception.InvalidCredentialsException;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.repository.AccountRepository;
import pl.edu.pwsztar.library.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean createAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail()) || accountRepository.existsByLogin(account.getLogin())) {
            return false;
        }
        accountRepository.save(account);
        return true;
    }

    @Override
    public Account logIn(String login, String password) throws InvalidCredentialsException {
        Account account = accountRepository.findByLogin(login);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        throw new InvalidCredentialsException();
    }
}
