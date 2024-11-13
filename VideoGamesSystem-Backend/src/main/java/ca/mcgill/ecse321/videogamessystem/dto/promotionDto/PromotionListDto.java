package ca.mcgill.ecse321.videogamessystem.dto.PromotionDto;

import java.util.List;

public class PromotionListDto {

    private List<PromotionSummaryDto> promotions;

    // Constructor that accepts a list of PromotionSummaryDto
    public PromotionListDto(List<PromotionSummaryDto> promotions) {
        this.promotions = promotions;
    }

    // Default protected constructor (for potential use by frameworks)
    protected PromotionListDto() {
    }

    // Getter for the list of promotions
    public List<PromotionSummaryDto> getPromotions() {
        return promotions;
    }

    // Setter for the list of promotions
    public void setPromotions(List<PromotionSummaryDto> promotions) {
        this.promotions = promotions;
    }

    // Method to get the number of promotions
    public Integer getNumberOfPromotions() {
        return promotions.size();
    }
}
