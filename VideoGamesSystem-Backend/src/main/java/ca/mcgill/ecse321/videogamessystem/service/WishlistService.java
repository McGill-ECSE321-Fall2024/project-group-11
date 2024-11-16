package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.exception.VideoGamesSystemException;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
import ca.mcgill.ecse321.videogamessystem.repository.WishlistRepository;

@Service
public class WishlistService {
    
    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     * @param customer
     * @return
     */
    @Transactional
    public Wishlist createWishlist(Customer customer) {
        if (customer == null) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Customer cannot be null.");
        }
        if (wishlistRepository.findWishlistByCustomer(customer) != null) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Customer already has a wishlist.");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setNbOfItems(0);
        wishlist.setCustomer(customer);
        return wishlistRepository.save(wishlist);
    }

    /**
     * @param id
     * @return
     */
    @Transactional
    public Wishlist getWishlistById(Long id) {
        Wishlist wishlist = wishlistRepository.findWishlistById(id);
        if (wishlist == null) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Wishlist not found.");
        }
        return wishlist;
    }

    /**
     * @param customer
     * @return
     */
    @Transactional
    public Wishlist getWishlistByCustomer(Customer customer) {
        if (customer == null) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Customer cannot be null.");
        }
        return wishlistRepository.findWishlistByCustomer(customer);
    }

    /**
     * @param id
     * @param nbOfItems
     * @return
     */
    @Transactional
    public Wishlist updateWishlistNbOfItems(Long id, int nbOfItems) {
        if (nbOfItems < 0) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Number of items cannot be negative.");
        }
        
        Wishlist wishlist = wishlistRepository.findWishlistById(id);
        if (wishlist == null) {
            throw new VideoGamesSystemException(HttpStatus.NOT_FOUND,"Wishlist not found.");
        }

        wishlist.setNbOfItems(nbOfItems);
        return wishlistRepository.save(wishlist);
    }

    // /**
    //  * @param id
    //  * @return
    //  */
    // @Transactional
    // public Wishlist deleteWishlist(Long id) {
    //     Wishlist wishlist = wishlistRepository.findWishlistById(id);
    //     if (wishlist == null) {
    //         throw new IllegalArgumentException("Wishlist not found.");
    //     }

    //     wishlist.setCustomer(null);  // Removing association with Customer
    //     wishlistRepository.delete(wishlist);
    //     return wishlist;
    // }

    // /**
    //  * @return
    //  */
    // @Transactional
    // public List<Wishlist> getAllWishlists() {
    //     return toList(wishlistRepository.findAll());
    // }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}


