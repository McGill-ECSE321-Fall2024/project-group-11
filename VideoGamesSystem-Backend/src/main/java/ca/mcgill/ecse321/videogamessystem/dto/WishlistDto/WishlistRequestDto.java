package ca.mcgill.ecse321.videogamessystem.dto.WishlistDto;
import java.util.List;

public class WishlistRequestDto {
    private int nbOfItems;
    private Long customerId; // Reference to the associated customer
    private List<Long> gamesId;

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

    public void setListOfGamesId(List<Long> gamesId){
        this.gamesId = gamesId;
    }

    public List<Long> getListOfGamesId(){
        return this.gamesId;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}