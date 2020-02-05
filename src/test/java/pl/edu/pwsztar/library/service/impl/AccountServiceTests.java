package pl.edu.pwsztar.library.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.edu.pwsztar.library.DTO.AccountCredentialsDTO;
import pl.edu.pwsztar.library.DTO.AccountDTO;
import pl.edu.pwsztar.library.exception.InvalidCredentialsException;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.repository.AccountRepository;
import pl.edu.pwsztar.library.service.AccountService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AccountServiceTests {

    AccountRepository accountRepository = new AccountRepository() {
        final Map<Long, Account> accounts = new ConcurrentHashMap<>();
        Long nextId = 0L;

        @Override
        public Account findByLogin(String login) {
            for (Map.Entry<Long,Account> entry : accounts.entrySet()){
                if (entry.getValue().getLogin().equals(login)){
                    return entry.getValue();
                }
            }
            return null;
        }

        @Override
        public Account findByEmail(String email) {
            return null;
        }

        @Override
        public void deleteByLogin(String login) {

        }

        @Override
        public void deleteByEmail(String email) {

        }

        @Override
        public Boolean existsByLogin(String login) {
            return accounts.values().stream()
                    .anyMatch(account -> account.getLogin().equals(login));
        }

        @Override
        public Boolean existsByEmail(String email) {
            return accounts.values().stream()
                    .anyMatch(account -> account.getEmail().equals(email));
        }

        @Override
        public List<Account> findAll() {
            return null;
        }

        @Override
        public List<Account> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Account> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public <S extends Account> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Account> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<Account> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Account getOne(Long aLong) {
            return null;
        }

        @Override
        public <S extends Account> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Account> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Account> S save(S s) {
            Long id = s.getId();
            if (id == null) {
                id = nextId++;
                s.setId(id);
            }
            accounts.put(id, s);
            return s;
        }

        @Override
        public Optional<Account> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Account account) {

        }

        @Override
        public void deleteAll(Iterable<? extends Account> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Account> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Account> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Account> boolean exists(Example<S> example) {
            return false;
        }
    };

    AccountService accountService = new AccountServiceImpl(accountRepository);

    public AccountDTO accountDTOInitliaizer() {
        return new AccountDTO("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    @Test
    public void createAccountWhenItDoesntExistTest() {
        AccountDTO accountDTO = accountDTOInitliaizer();
        assertEquals("Creating account", true, accountService.createAccount(new Account(accountDTO)));
    }

    @Test
    public void createAccountWhenLoginOrEmailIsTaken() {
        AccountDTO accountDTO = accountDTOInitliaizer();
        accountService.createAccount(new Account(accountDTO));
        accountDTO.setEmail("d");
        assertEquals("Create account when Login is taken", false, accountService.createAccount(new Account(accountDTO)));
        accountDTO.setLogin("ddd");
        accountDTO.setEmail("5");
        assertEquals("Create account when Email is taken", false, accountService.createAccount(new Account(accountDTO)));
    }

    @Test
    public void signInWhenLoginCredentialsAreCorrect() {
        AccountDTO accountDTO = accountDTOInitliaizer();
        accountService.createAccount(new Account(accountDTO));
        Account account = accountService.logIn(new AccountCredentialsDTO(accountDTO.getLogin(), accountDTO.getPassword()));
        assertEquals("sign in with correct credentials", accountDTO.getPassword(), account.getPassword());
    }

    @Test
    public void signInWhenLoginCredentialsAreWrong(){
        Assertions.assertThrows(InvalidCredentialsException.class, () -> accountService.logIn(new AccountCredentialsDTO("wrong", "wrong")));
    }

}

