package pl.edu.pwsztar.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.library.DTO.ReviewDTO;
import pl.edu.pwsztar.library.DTO.ReviewInfoDTO;
import pl.edu.pwsztar.library.model.Review;
import pl.edu.pwsztar.library.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews/")
public class ReviewController {

    final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("addReview")
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO) {
        if (reviewService.addReview(reviewDTO.getAccountId(), reviewDTO.getBookId(), reviewDTO.getGrade(), reviewDTO.getContent())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @GetMapping("{bookId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsForBook(@PathVariable("bookId") long bookId) {
        List<ReviewDTO> reviews = new ArrayList<>();
        for (Review review : reviewService.getReviewsForBook(bookId)){
            reviews.add(new ReviewDTO(review.getAccount().getId(),review.getBook().getId(),review.getGrade(), review.getContent()));
        }
        if (reviews.size() > 0) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("reviewsInfo/{bookId}")
    public ResponseEntity<ReviewInfoDTO> getReviewsInfo(@PathVariable("bookId") long bookId) {
        if (reviewService.getReviewsCountForBook(bookId) > 0) {
            return new ResponseEntity<>(new ReviewInfoDTO(reviewService.getAverageGradeForBook(bookId),
                    reviewService.getReviewsCountForBook(bookId)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
