package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.library.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);

    Account findByEmail(String email);

    void deleteByLogin(String login);

    void deleteByEmail(String email);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);
}
