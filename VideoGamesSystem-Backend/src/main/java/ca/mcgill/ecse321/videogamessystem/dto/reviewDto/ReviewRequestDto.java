package ca.mcgill.ecse321.videogamessystem.dto.reviewDto;

import java.sql.Date;

public class ReviewRequestDto {
    private String reviewContent;
    private int gameRating;
    private Date reviewDate;

    public ReviewRequestDto(String reviewContent, int gameRating, Date reviewDate) {
        this.reviewContent = reviewContent;
        this.gameRating = gameRating;
        this.reviewDate = reviewDate;
    }

    // Getters and Setters
    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent){
        this.reviewContent = reviewContent;
    }

    public int getReviewRating(){
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

    
}
