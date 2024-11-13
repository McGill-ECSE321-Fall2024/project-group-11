package ca.mcgill.ecse321.videogamessystem.dto.ReviewDto;

import java.sql.Date;

import ca.mcgill.ecse321.videogamessystem.model.Review;

public class ReviewSummaryDto {

    private String reviewContent;
    private int gameRating;
    private Date reviewDate;

    // Constructor to initialize from review attributes
    public ReviewSummaryDto(String reviewContent, int gameRating, Date reviewDate) {
        this.reviewContent = reviewContent;
        this.gameRating = gameRating;
        this.reviewDate = reviewDate;
    }

    // Optional constructor to initialize directly from a Review object
    public ReviewSummaryDto(Review review) {
        this.reviewContent = review.getReviewContent();
        this.gameRating = review.getGameRating();
        this.reviewDate = review.getReviewDate();
    }

    // Getters
    public String getReviewContent() {
        return reviewContent;
    }

    public int getGameRating() {
        return gameRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }
}

