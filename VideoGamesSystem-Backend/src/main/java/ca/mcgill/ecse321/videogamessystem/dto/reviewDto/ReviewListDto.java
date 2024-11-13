package ca.mcgill.ecse321.videogamessystem.dto.ReviewDto;

import java.util.List;

public class ReviewListDto {

    private List<ReviewSummaryDto> reviews;

    // Constructor that accepts a list of ReviewSummaryDto
    public ReviewListDto(List<ReviewSummaryDto> reviews) {
        this.reviews = reviews;
    }

    // Default protected constructor (for potential use by frameworks)
    protected ReviewListDto() {
    }

    // Getter for the list of reviews
    public List<ReviewSummaryDto> getReviews() {
        return reviews;
    }

    // Setter for the list of reviews
    public void setReviews(List<ReviewSummaryDto> reviews) {
        this.reviews = reviews;
    }

    // Method to get the number of reviews
    public Integer getNumberOfReviews() {
        return reviews != null ? reviews.size() : 0;
    }
}
