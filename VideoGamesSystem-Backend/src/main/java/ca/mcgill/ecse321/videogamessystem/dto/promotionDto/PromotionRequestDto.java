package ca.mcgill.ecse321.videogamessystem.dto.PromotionDto;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.FutureOrPresent;

public class PromotionRequestDto {

    @NotNull(message = "Percentage cannot be null")
    @Positive(message = "Percentage must be positive")
    private int percentage;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private Date startDate;

    @FutureOrPresent(message = "End date must be today or in the future")
    private Date endDate;

    // Constructor
    public PromotionRequestDto(int percentage, Date startDate, Date endDate) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PromotionRequestDto() {
        super();
    }

    // Getters and Setters
    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
