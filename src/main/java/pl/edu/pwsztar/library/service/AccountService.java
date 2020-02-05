package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.DTO.AccountCredentialsDTO;
import pl.edu.pwsztar.library.model.Account;

public interface AccountService {

    boolean createAccount(Account account);

    Account logIn(AccountCredentialsDTO accountCredentialsDTO);

    boolean isAdmin(Long accountId);

    Account createMockAdmin();
}
