package pl.edu.pwsztar.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.Review;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

//    maybe later
//    List<Book> findTop10Books();
}
