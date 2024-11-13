package ca.mcgill.ecse321.videogamessystem.dto.SpecificReviewRatingDto;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.model.Customer;

public class SpecificReviewRatingRequestDto {

    private ReviewRating reviewRating;
    private Review review;
    private Long reviewId;
    private Customer customer;
    private Long customerId;
    private String customerUserName;

    // Constructor with all attributes
    public SpecificReviewRatingRequestDto(ReviewRating reviewRating, Review review, Long reviewId, Customer customer, Long customerId, String customerUserName) {
        this.reviewRating = reviewRating;
        this.review = review;
        this.reviewId = reviewId;
        this.customer = customer;
        this.customerId = customerId;
        this.customerUserName = customerUserName;
    }

    // Getters and Setters
    public ReviewRating getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(ReviewRating reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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