package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.Review;
import com.ipn.mx.domain.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody Review review) {
        // Create a new review
        reviewService.createReview(review);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Review getReview(@PathVariable Long id) {
        // Get review by id
        return reviewService.getReview(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long id) {
        // Delete an existing review
        reviewService.deleteReview(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@PathVariable Long id, @RequestBody Review review) {
        // Update a review
        if (!id.equals(review.getIdReview())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        reviewService.updateReview(id, review);
    }
}
