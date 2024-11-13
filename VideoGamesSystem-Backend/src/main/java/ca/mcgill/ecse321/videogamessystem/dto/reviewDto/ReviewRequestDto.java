package ca.mcgill.ecse321.videogamessystem.dto.ReviewDto;

import java.sql.Date;

import ca.mcgill.ecse321.videogamessystem.model.Review;

public class ReviewRequestDto {
    private String reviewContent;
    private int gameRating;
    private Date reviewDate;
    private Long customerId;
    private String customerUserName;
    private Review parentReview;
    private Long gameId;
    private String gameTitle;

    // Constructor with all attributes
    public ReviewRequestDto(String reviewContent, int gameRating, Date reviewDate, Long customerId, String customerUserName, Review parentReview, Long gameId, String gameTitle) {
        this.reviewContent = reviewContent;
        this.gameRating = gameRating;
        this.reviewDate = reviewDate;
        this.customerId = customerId;
        this.customerUserName = customerUserName;
        this.parentReview = parentReview;
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    // Getters and Setters
    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getGameRating() {
        return gameRating;
    }

    public void setGameRating(int gameRating) {
        this.gameRating = gameRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
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

    public Review getParent() {
        return parentReview;
    }

    public void setParent(Review parentReview) {
        this.parentReview = parentReview;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}