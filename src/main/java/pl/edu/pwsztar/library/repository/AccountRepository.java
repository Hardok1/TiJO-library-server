package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.library.model.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);

    Optional<Account> findByEmail(String email);

    void deleteByLogin(String login);

    void deleteByEmail(String email);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
