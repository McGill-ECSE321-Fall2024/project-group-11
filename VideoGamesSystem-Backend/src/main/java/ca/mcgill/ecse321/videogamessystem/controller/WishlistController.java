package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.WishlistDto.WishlistRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.WishlistDto.WishlistResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
import ca.mcgill.ecse321.videogamessystem.service.WishlistService;
import ca.mcgill.ecse321.videogamessystem.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private CustomerService customerService;

    /**
     * Create a new wishlist for a customer.
     * 
     * @param requestDto WishlistRequestDto containing customer information.
     * @return WishlistResponseDto with the created wishlist details.
     */
    @PostMapping
    public WishlistResponseDto createWishlist(@Valid @RequestBody WishlistRequestDto requestDto) {
        Customer customer = customerService.getCustomerById(requestDto.getCustomerId());
        Wishlist wishlist = wishlistService.createWishlist(customer);
        return new WishlistResponseDto(wishlist);
    }

    /**
     * Get a wishlist by its ID.
     * 
     * @param id the ID of the wishlist.
     * @return WishlistResponseDto with the requested wishlist details.
     */
    @GetMapping("/{id}")
    public WishlistResponseDto getWishlistById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        return new WishlistResponseDto(wishlist);
    }

    /**
     * Get all wishlists.
     * 
     * @return List of WishlistResponseDto with details of all wishlists.
     */
    @GetMapping
    public List<WishlistResponseDto> getAllWishlists() {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        return wishlists.stream()
            .map(WishlistResponseDto::new)
            .collect(Collectors.toList());
    }

    /**
     * Get a wishlist by customer ID.
     * 
     * @param customerId the ID of the customer.
     * @return WishlistResponseDto with the details of the wishlist for the specified customer.
     */
    @GetMapping("/customer/{customerId}")
    public WishlistResponseDto getWishlistByCustomerId(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        Wishlist wishlist = wishlistService.getWishlistByCustomer(customer);
        return new WishlistResponseDto(wishlist);
    }

    /**
     * Update the number of items in a wishlist.
     * 
     * @param id the ID of the wishlist to update.
     * @param nbOfItems the new number of items to set in the wishlist.
     * @return WishlistResponseDto with the updated wishlist details.
     */
    @PutMapping("/{id}")
    public WishlistResponseDto updateWishlistNbOfItems(@PathVariable Long id, @RequestParam int nbOfItems) {
        Wishlist wishlist = wishlistService.updateWishlistNbOfItems(id, nbOfItems);
        return new WishlistResponseDto(wishlist);
    }

    /**
     * Delete a wishlist by its ID.
     * 
     * @param id the ID of the wishlist to delete.
     * @return WishlistResponseDto with the details of the deleted wishlist.
     */
    @DeleteMapping("/{id}")
    public WishlistResponseDto deleteWishlist(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.deleteWishlist(id);
        return new WishlistResponseDto(wishlist);
    }
}
