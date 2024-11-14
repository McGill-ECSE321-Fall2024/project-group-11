package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Create a new review.
     * 
     * @param request the review request DTO containing the review details
     * @return the response DTO with the created review details
     */
    @PostMapping
    public ReviewResponseDto createReview(@Valid @RequestBody ReviewRequestDto request) {
        Review review = reviewService.createReview(
                request.getGameRating(),
                request.getReviewContent(),
                request.getCustomerId(),
                request.getParentReview() != null ? request.getParentReview().getId() : null
        );
        return new ReviewResponseDto(review);
    }

    /**
     * Retrieve a review by its ID.
     * 
     * @param id the ID of the review
     * @return the response DTO with the review details
     */
    @GetMapping("/{id}")
    public ReviewResponseDto getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return new ReviewResponseDto(review);
    }

    /**
     * Retrieve all reviews.
     * 
     * @return a list of response DTOs with the details of all reviews
     */
    @GetMapping
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve reviews by review date.
     * 
     * @param reviewDate the date of the reviews to retrieve
     * @return a list of response DTOs with the details of the reviews
     */
    @GetMapping("/date/{reviewDate}")
    public List<ReviewResponseDto> getReviewsByReviewDate(@PathVariable Date reviewDate) {
        List<Review> reviews = reviewService.getReviewsByReviewDate(reviewDate);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve reviews by game rating.
     * 
     * @param gameRating the game rating of the reviews to retrieve
     * @return a list of response DTOs with the details of the reviews
     */
    @GetMapping("/rating/{gameRating}")
    public List<ReviewResponseDto> getReviewsByGameRating(@PathVariable int gameRating) {
        List<Review> reviews = reviewService.getReviewsByGameRating(gameRating);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve reviews by customer ID.
     * 
     * @param customerId the ID of the customer
     * @return a list of response DTOs with the details of the reviews
     */
    @GetMapping("/customer/{customerId}")
    public List<ReviewResponseDto> getReviewsByCustomer(@PathVariable Long customerId) {
        List<Review> reviews = reviewService.getReviewsByCustomer(customerId);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve reviews by parent review ID.
     * 
     * @param parentReviewId the ID of the parent review
     * @return a list of response DTOs with the details of the child reviews
     */
    @GetMapping("/parent/{parentReviewId}")
    public List<ReviewResponseDto> getReviewsByParentReview(@PathVariable Long parentReviewId) {
        List<Review> reviews = reviewService.getReviewsByParentReview(parentReviewId);
        return reviews.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Update the content of a review.
     * 
     * @param id the ID of the review
     * @param newContent the new content for the review
     * @return the response DTO with the updated review details
     */
    @PutMapping("/{id}/content")
    public ReviewResponseDto updateReviewContent(@PathVariable Long id, @RequestParam String newContent) {
        Review updatedReview = reviewService.updateReviewContent(id, newContent);
        return new ReviewResponseDto(updatedReview);
    }

    /**
     * Update the review date.
     * 
     * @param id the ID of the review
     * @param newDate the new date for the review
     * @return the response DTO with the updated review details
     */
    @PutMapping("/{id}/date")
    public ReviewResponseDto updateReviewDate(@PathVariable Long id, @RequestParam Date newDate) {
        Review updatedReview = reviewService.updateReviewDate(id, newDate);
        return new ReviewResponseDto(updatedReview);
    }

    /**
     * Update the game rating of a review.
     * 
     * @param id the ID of the review
     * @param newRating the new rating for the review
     * @return the response DTO with the updated review details
     */
    @PutMapping("/{id}/rating")
    public ReviewResponseDto updateGameRating(@PathVariable Long id, @RequestParam int newRating) {
        Review updatedReview = reviewService.updateGameRating(id, newRating);
        return new ReviewResponseDto(updatedReview);
    }

    /**
     * Delete a review by its ID.
     * 
     * @param id the ID of the review to delete
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
