package ca.mcgill.ecse321.videogamessystem.dto.PromotionDto;

import java.sql.Date;

public class PromotionRequestDto {
    private int percentage;
    private Date startDate;
    private Date endDate;

    public PromotionRequestDto(int percentage, Date startDate, Date endDate) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
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

