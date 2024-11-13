package ca.mcgill.ecse321.videogamessystem.dto.SpecificReviewRatingDto;

import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating;
import ca.mcgill.ecse321.videogamessystem.model.SpecificReviewRating.ReviewRating;

public class SpecificReviewRatingResponseDto {

    private Long id;
    private ReviewRating reviewRating;

   // private Long reviewId;    // ID of the associated Review
   // private Long customerId;  // ID of the associated Customer

    // Default constructor for frameworks
    public SpecificReviewRatingResponseDto() {
    }

    // Constructor that accepts SpecificReviewRating attributes
    public SpecificReviewRatingResponseDto(Long id, ReviewRating reviewRating, Long reviewId, Long customerId) {
        this.id = id;
        this.reviewRating = reviewRating;
        //this.reviewId = reviewId;
        //this.customerId = customerId;
    }

    // Constructor that accepts a SpecificReviewRating model object
    public SpecificReviewRatingResponseDto(SpecificReviewRating specificReviewRating) {
        this.id = specificReviewRating.getId();// implement the get id 
        this.reviewRating = specificReviewRating.getReviewRating();
        //this.reviewId = specificReviewRating.getReview() != null ? specificReviewRating.getReview().getId() : null;
        //this.customerId = specificReviewRating.getCustomer() != null ? specificReviewRating.getCustomer().getId() : null;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public ReviewRating getReviewRating() {
        return reviewRating;
    }
/* 
    public Long getReviewId() {
        return reviewId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    */
}
