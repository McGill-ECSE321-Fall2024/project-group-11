package ca.mcgill.ecse321.videogamessystem.dto.ReviewDto;

import ca.mcgill.ecse321.videogamessystem.model.Review;
import java.sql.Date;

public class ReviewResponseDto {

    private Long id;
    private int gameRating;
    private String reviewContent;
    private Date reviewDate;
    private Long customerId;
    private Long gameId;
    private Long parentReviewId;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.gameRating = review.getGameRating();
        this.reviewContent = review.getReviewContent();
        this.reviewDate = review.getReviewDate();
        this.customerId = review.getCustomer() != null ? review.getCustomer().getId() : null;
        this.gameId = review.getGame() != null ? review.getGame().getId() : null;
        this.parentReviewId = review.getParentReview() != null ? review.getParentReview().getId() : null;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public int getGameRating() {
        return gameRating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getParentReviewId() {
        return parentReviewId;
    }
}
