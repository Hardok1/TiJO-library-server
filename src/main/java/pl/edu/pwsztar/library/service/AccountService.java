package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.model.Account;

public interface AccountService {

    boolean createAccount(Account account);

    Account logIn(String login, String password);
}
