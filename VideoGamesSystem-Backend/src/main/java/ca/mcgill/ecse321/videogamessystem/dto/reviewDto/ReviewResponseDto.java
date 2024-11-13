package ca.mcgill.ecse321.videogamessystem.dto.ReviewDto;

import java.sql.Date;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Review;

public class ReviewResponseDto {
    private Long id;
    private String reviewContent;
    private int gameRating;
    private Date reviewDate;
    private Customer customer;
    private Long customerId;
    private Review parentReview;
    private Long parentReviewId;

    // Default constructor for frameworks
    public ReviewResponseDto() {
    }

    // Constructor with all attributes
    public ReviewResponseDto(Long id, String reviewContent, int gameRating, Date reviewDate, Customer customer, Review parentReview) {
        this.id = id;
        this.reviewContent = reviewContent;
        this.gameRating = gameRating;
        this.reviewDate = reviewDate;
        this.customer = customer;
        //this.customerId = customer != null ? customer.getId() : null;
        this.parentReview = parentReview;
        //this.parentReviewId = parentReview != null ? parentReview.getId() : null;
    }

    // Constructor that accepts a Review model object
    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.reviewContent = review.getReviewContent();
        this.gameRating = review.getGameRating();
        this.reviewDate = review.getReviewDate();
        this.customer = review.getCustomer();
        this.customerId = review.getCustomer() != null ? review.getCustomer().getId() : null;
        this.parentReview = review.getParentReview();
        this.parentReviewId = review.getParentReview() != null ? review.getParentReview().getId() : null;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public int getGameRating() {
        return gameRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Review getParentReview() {
        return parentReview;
    }

    public Long getParentReviewId() {
        return parentReviewId;
    }

   
}
