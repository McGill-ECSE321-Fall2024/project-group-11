package ca.mcgill.ecse321.videogamessystem.dto.WishlistDto;
import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerResponseDto;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;

public class WishlistResponseDto {
    private Long id;
    private int nbOfItems;

    private CustomerResponseDto customer;

    protected WishlistResponseDto(){} 

    // Constructor that initializes fields directly from a Wishlist entity
    public WishlistResponseDto(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.nbOfItems = wishlist.getNbOfItems();
        
        this.customer = CustomerResponseDto.convertToCustomerResponseDto(wishlist.getCustomer());
    }

    // Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbOfItems() {
        return nbOfItems;
    }

    public void setNbOfItems(int nbOfItems) {
        this.nbOfItems = nbOfItems;
    }

    public CustomerResponseDto getCustomerResponseDto(){
        return this.customer;
    }

    public static WishlistResponseDto convertToWishlistResponseDto(Wishlist wishlist) {
        if (wishlist == null) {
            throw new IllegalArgumentException("Wishlist cannot be null.");
        }
    
        WishlistResponseDto dto = new WishlistResponseDto(wishlist);
        dto.setId(wishlist.getId());
        dto.setNbOfItems(wishlist.getNbOfItems());
    
        Customer customer = wishlist.getCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
    
        return dto;
    }
}