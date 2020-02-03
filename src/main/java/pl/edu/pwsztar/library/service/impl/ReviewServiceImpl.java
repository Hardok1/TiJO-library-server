package pl.edu.pwsztar.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.library.model.Account;
import pl.edu.pwsztar.library.model.Book;
import pl.edu.pwsztar.library.model.Review;
import pl.edu.pwsztar.library.repository.AccountRepository;
import pl.edu.pwsztar.library.repository.BookRepository;
import pl.edu.pwsztar.library.repository.ReviewRepository;
import pl.edu.pwsztar.library.service.ReviewService;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class ReviewServiceImpl implements ReviewService {

    final ReviewRepository reviewRepository;
    final BookRepository bookRepository;
    final AccountRepository accountRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository, AccountRepository accountRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Review> getReviewsForBook(Long bookId) {
//        Optional<Book> book = bookRepository.findById(bookId);
//        return book.map(Book::getReview).orElse(new ArrayList<>());
        return reviewRepository.findAllByBookId(bookId);
    }

    @Override
    public boolean addReview(Long accountId, Long bookId, int grade, String content) {
        Optional<Account> account = accountRepository.findById(accountId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (account.isPresent() && book.isPresent()) {
            for (Review review : getReviewsForBook(bookId)){
                if (review.getAccount().equals(account.get())){
                    return false;
                }
            }
            Review review = new Review();
            review.setAccount(account.get());
            review.setBook(book.get());
            review.setContent(content);
            review.setGrade(grade);
            review.setDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Integer getAverageGradeForBook(Long bookId) {
        int gradeSum = 0;
        for (Review review : getReviewsForBook(bookId)){
            gradeSum = review.getGrade();
        }
        return gradeSum/getReviewsCountForBook(bookId);
    }

    @Override
    public Integer getReviewsCountForBook(Long bookId) {
        return getReviewsForBook(bookId).size();
    }
}