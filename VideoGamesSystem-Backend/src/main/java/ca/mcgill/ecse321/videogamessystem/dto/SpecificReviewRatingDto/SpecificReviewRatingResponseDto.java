package ca.mcgill.ecse321.videogamessystem.dto.SpecificReviewRatingDto;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.model.Customer;

public class SpecificReviewRatingResponseDto {

    private Long reviewRatingId;      // ID of the SpecificReviewRating entity
    private ReviewRating reviewRating;
    private Long reviewId;            // ID of the associated Review
    private Review review;
    private Long customerId;          // ID of the associated Customer
    private Customer customer;
    private String customerUserName;  // Username of the Customer for display purposes

    // Constructor to initialize all fields
    public SpecificReviewRatingResponseDto(Long reviewRatingId, ReviewRating reviewRating, Customer customer, Review review) {
        this.reviewRatingId = reviewRatingId;
        this.reviewRating = reviewRating;
        this.review = review;
        this.customer = customer;
    }

    // Constructor to create a response DTO from a SpecificReviewRating model object
    public SpecificReviewRatingResponseDto(SpecificReviewRating specificReviewRating) {
        this.reviewRatingId = specificReviewRating.getId(); // Assuming there's a getId() method in SpecificReviewRating
        this.reviewRating = specificReviewRating.getReviewRating();
        
        Review review = specificReviewRating.getReview();
        this.reviewId = review != null ? review.getId() : null;
        
        Customer customer = specificReviewRating.getCustomer();
        this.customerId = customer != null ? customer.getId() : null;
        this.customerUserName = customer != null ? customer.getUserName() : null;
    }

    // Getters and Setters
    public Long getReviewRatingId() {
        return reviewRatingId;
    }

    public void setReviewRatingId(Long reviewRatingId) {
        this.reviewRatingId = reviewRatingId;
    }

    public ReviewRating getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }
}
