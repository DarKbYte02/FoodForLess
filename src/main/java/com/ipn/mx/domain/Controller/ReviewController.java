package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Review;
import com.ipn.mx.domain.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public void createReview(@RequestBody Review review) {
        // Create a new review
        reviewService.createReview(review);
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable Long id) {
        // Get review by id
        return reviewService.getReview(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        // Delete an existing review
        reviewService.deleteReview(id);
    }

    @GetMapping
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }
}
