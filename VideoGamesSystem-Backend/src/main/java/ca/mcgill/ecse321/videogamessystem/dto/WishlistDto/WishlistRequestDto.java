package ca.mcgill.ecse321.videogamessystem.dto.WishlistDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WishlistRequestDto {

    @Positive(message = "Number of items must be positive.")
    private int nbOfItems;

    @NotNull(message = "Customer ID cannot be null.")
    private Long customerId; // Reference to the associated customer

    public int getNbOfItems() {
        return nbOfItems;
    }

    public void setNbOfItems(int nbOfItems) {
        this.nbOfItems = nbOfItems;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}