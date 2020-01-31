package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.model.BorrowedBook;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

    List<BorrowedBook> findAllByAccount(Account account);
}
