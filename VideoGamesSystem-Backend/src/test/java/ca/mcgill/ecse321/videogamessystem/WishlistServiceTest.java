package ca.mcgill.ecse321.videogamessystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.repository.WishlistRepository;
import ca.mcgill.ecse321.videogamessystem.service.WishlistService;

@SpringBootTest
public class WishlistServiceTest {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Clear database and set up a sample customer for testing
        wishlistRepository.deleteAll();
        customerRepository.deleteAll();

        customer = new Customer();
        customer.setUserName("Test User");
        customer.setEmail("test.user@gmail.com");
        customer.setPassword("password123");
        customer = customerRepository.save(customer);
    }

    @AfterEach
    public void clearDatabase() {
        wishlistRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testCreateWishlist() {
        // Create wishlist
        Wishlist wishlist = wishlistService.createWishlist(customer);

        // Assertions
        assertNotNull(wishlist);
        assertEquals(customer.getId(), wishlist.getCustomer().getId());
        assertEquals(0, wishlist.getNbOfItems());
    }

    @Test
    public void testCreateWishlistForCustomerWithExistingWishlist() {
        // Create an initial wishlist for the customer
        wishlistService.createWishlist(customer);

        // Attempt to create another wishlist for the same customer
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wishlistService.createWishlist(customer);
        });

        // Verify the exception message
        assertEquals("Customer already has a wishlist.", exception.getMessage());
    }

    @Test
    public void testCreateWishlistWithNullCustomer() {
        // Attempt to create a wishlist with a null customer
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wishlistService.createWishlist(null);
        });

        // Verify the exception message
        assertEquals("Customer cannot be null.", exception.getMessage());
    }

    @Test
    public void testGetWishlistById() {
        // Create and save a wishlist to retrieve
        Wishlist wishlist = wishlistService.createWishlist(customer);

        // Retrieve wishlist by ID
        Wishlist retrievedWishlist = wishlistService.getWishlistById(wishlist.getId());

        // Assertions
        assertNotNull(retrievedWishlist);
        assertEquals(wishlist.getNbOfItems(), retrievedWishlist.getNbOfItems());
        assertEquals(customer.getId(), retrievedWishlist.getCustomer().getId());
    }

    @Test
    public void testGetWishlistByCustomer() {
        // Create and save a wishlist
        wishlistService.createWishlist(customer);

        // Retrieve wishlist by customer
        Wishlist retrievedWishlist = wishlistService.getWishlistByCustomer(customer);

        // Assertions
        assertNotNull(retrievedWishlist);
        assertEquals(customer.getId(), retrievedWishlist.getCustomer().getId());
    }

    @Test
    public void testUpdateWishlistNbOfItems() {
        // Create and save a wishlist
        Wishlist wishlist = wishlistService.createWishlist(customer);

        // Update number of items
        int newNbOfItems = 5;
        Wishlist updatedWishlist = wishlistService.updateWishlistNbOfItems(wishlist.getId(), newNbOfItems);

        // Assertions
        assertNotNull(updatedWishlist);
        assertEquals(newNbOfItems, updatedWishlist.getNbOfItems());
    }

    @Test
    public void testUpdateWishlistNbOfItemsWithNegativeValue() {
        // Create and save a wishlist
        Wishlist wishlist = wishlistService.createWishlist(customer);

        // Attempt to update number of items with a negative value
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wishlistService.updateWishlistNbOfItems(wishlist.getId(), -1);
        });

        // Verify the exception message
        assertEquals("Number of items cannot be negative.", exception.getMessage());
    }

    @Test
    public void testDeleteExistingWishlist() {
        // Create and save a wishlist to delete
        Wishlist wishlist = wishlistService.createWishlist(customer);

        // Delete the wishlist
        Wishlist deletedWishlist = wishlistService.deleteWishlist(wishlist.getId());

        // Assertions
        assertNotNull(deletedWishlist); // Check that we have a non-null return
        assertEquals(wishlist.getId(), deletedWishlist.getId()); // Confirm the correct wishlist was deleted
        assertNull(deletedWishlist.getCustomer()); // Customer should be null after deletion
        assertEquals(wishlist.getNbOfItems(), deletedWishlist.getNbOfItems()); // Check that number of items matches
}

    @Test
    public void testDeleteNonExistingWishlist() {
        // Attempt to delete a non-existing wishlist
        Long nonExistingId = 9999L;

        // Expect an exception due to non-existing wishlist
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            wishlistService.deleteWishlist(nonExistingId);
        });

        // Verify the exception message
        assertEquals("Wishlist not found.", exception.getMessage());
    }
}
