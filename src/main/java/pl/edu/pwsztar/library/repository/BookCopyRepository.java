package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.BookCopy;

import java.util.Optional;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Long countByBookAndBorrowed(Book book, boolean borrowed);
    Optional<BookCopy> findFirstByBookAndBorrowed(Book book, boolean borrowed);
}
