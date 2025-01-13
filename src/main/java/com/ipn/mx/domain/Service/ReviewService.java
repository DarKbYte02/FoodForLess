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
        if (id <= 0 || reviewRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Review inválido");
        }
        reviewRepository.deleteById(id);
    }

    public Review updateReview(Long id, Review review) {
        // Update a review
        if (!id.equals(review.getIdReview())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        Review existingReview = reviewRepository.findById(review.getIdReview()).orElse(null);
        if (existingReview == null) {
            throw new IllegalArgumentException("Review no encontrado");
        }
        existingReview.setCalificacion(review.getCalificacion());
        existingReview.setDescripcionReview(review.getDescripcionReview());
        existingReview.setImagenReview(review.getImagenReview());

        return reviewRepository.save(existingReview);
    }

    public Review getReview(Long id) {
        // Get a review
        if (id <= 0 || reviewRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Review no encontrado");
        }
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviews() {
        // Get all reviews
        return reviewRepository.findAll();
    }
}
