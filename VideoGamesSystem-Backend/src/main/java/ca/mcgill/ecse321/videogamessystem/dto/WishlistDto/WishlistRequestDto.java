package ca.mcgill.ecse321.videogamessystem.dto.WishlistDto;

public class WishlistRequestDto {
    private int nbOfItems;
    private Long customerId; // Reference to the associated customer

    public WishlistRequestDto(int nbOfItems, Long customerId) {
        this.nbOfItems = nbOfItems;
        this.customerId = customerId;
    }

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