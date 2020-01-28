package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Long countByBook(Book book);
}
