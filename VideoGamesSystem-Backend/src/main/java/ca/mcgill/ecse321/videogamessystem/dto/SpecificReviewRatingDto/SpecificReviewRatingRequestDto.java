package ca.mcgill.ecse321.videogamessystem.dto.SpecificReviewRatingDto;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;

public class SpecificReviewRatingRequestDto {

    private ReviewRating reviewRating;

    // Constructor
    public SpecificReviewRatingRequestDto(ReviewRating reviewRating){
        this.reviewRating = reviewRating;
    }

    // Getters and Setters
    public ReviewRating getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(ReviewRating reviewRating){
        this.reviewRating = reviewRating;
    }
}
