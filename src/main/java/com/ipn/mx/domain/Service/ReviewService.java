package com.ipn.mx.domain.Service;

import com.ipn.mx.domain.Entity.Review;
import com.ipn.mx.domain.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void createReview(Review review) {
        // Create a review
        reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        // Delete a review
        reviewRepository.deleteById(id);
    }

    public Review getReview(Long id) {
        // Get a review
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviews() {
        // Get all reviews
        return reviewRepository.findAll();
    }
}
