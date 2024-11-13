package ca.mcgill.ecse321.videogamessystem.dto.reviewDto;

import java.sql.Date;
import ca.mcgill.ecse321.videogamessystem.model.Review;

public class ReviewResponseDto {
    private Long id;
    private String reviewContent;
    private int gameRating;
    private Date reviewDate;
    /* 
    private Long parentReviewId;  // ID of the parent review, if any
    private Long customerId;      // ID of the customer who wrote the review
*/
    // Default constructor for frameworks
    public ReviewResponseDto() {
    }

    // Constructor that accepts a Review model object
    public ReviewResponseDto(Review review) {
        this.id = review.getId(); // fix with model
        this.reviewContent = review.getReviewContent();
        this.gameRating = review.getGameRating();
        this.reviewDate = review.getReviewDate();
       
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
/* 
    public Long getParentReviewId() {
        return parentReviewId;
    }

    public Long getCustomerId() {
        return customerId;
    }
*/
   
}
