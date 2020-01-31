package pl.edu.pwsztar.library.service;

import pl.edu.pwsztar.library.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsForBook(Long bookId);

    boolean addReview(Long accountId, Long bookId, int grade, String content);

    Integer getAverageGradeForBook(Long bookId);

    Integer getReviewsCountForBook(Long bookId);
}
