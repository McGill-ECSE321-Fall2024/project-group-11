// package ca.mcgill.ecse321.videogamessystem.servicetest;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import ca.mcgill.ecse321.videogamessystem.exception.VideoGamesSystemException;
// import ca.mcgill.ecse321.videogamessystem.model.Customer;
// import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
// import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
// import ca.mcgill.ecse321.videogamessystem.repository.WishlistRepository;
// import ca.mcgill.ecse321.videogamessystem.service.WishlistService;
// import org.springframework.http.HttpStatus;

// @SpringBootTest
// public class WishlistServiceTest {

//     @Autowired
//     private WishlistService wishlistService;

//     @Autowired
//     private WishlistRepository wishlistRepository;

//     @Autowired
//     private CustomerRepository customerRepository;

//     private Customer customer;

//     @BeforeEach
//     public void setUp() {
//         wishlistRepository.deleteAll();
//         customerRepository.deleteAll();

//         customer = new Customer();
//         customer.setUserName("Test User");
//         customer.setEmail("test.user@gmail.com");
//         customer.setPassword("password123");
//         customer = customerRepository.save(customer);
//     }

//     @AfterEach
//     public void clearDatabase() {
//         wishlistRepository.deleteAll();
//         customerRepository.deleteAll();
//     }

//     @Test
//     public void testCreateWishlist() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);

//         assertNotNull(wishlist);
//         assertEquals(customer.getId(), wishlist.getCustomer().getId());
//         assertEquals(0, wishlist.getNbOfItems());
//     }

//     @Test
//     public void testCreateWishlistForCustomerWithExistingWishlist() {
//         wishlistService.createWishlist(customer);

//         VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
//             wishlistService.createWishlist(customer);
//         });
//         assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
//         assertEquals("Customer already has a wishlist.", exception.getMessage());
//     }

//     @Test
//     public void testCreateWishlistWithNullCustomer() {
//         VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
//             wishlistService.createWishlist(null);
//         });
//         assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
//         assertEquals("Customer cannot be null.", exception.getMessage());
//     }

//     @Test
//     public void testGetWishlistById() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);
//         Wishlist retrievedWishlist = wishlistService.getWishlistById(wishlist.getId());

//         assertNotNull(retrievedWishlist);
//         assertEquals(wishlist.getNbOfItems(), retrievedWishlist.getNbOfItems());
//         assertEquals(customer.getId(), retrievedWishlist.getCustomer().getId());
//     }

//     @Test
//     public void testGetWishlistByCustomer() {
//         wishlistService.createWishlist(customer);
//         Wishlist retrievedWishlist = wishlistService.getWishlistByCustomer(customer);

//         assertNotNull(retrievedWishlist);
//         assertEquals(customer.getId(), retrievedWishlist.getCustomer().getId());
//     }

//     @Test
//     public void testUpdateWishlistNbOfItems() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);
//         int newNbOfItems = 5;
//         Wishlist updatedWishlist = wishlistService.updateWishlistNbOfItems(wishlist.getId(), newNbOfItems);

//         assertNotNull(updatedWishlist);
//         assertEquals(newNbOfItems, updatedWishlist.getNbOfItems());
//     }

//     @Test
//     public void testUpdateWishlistNbOfItemsWithNegativeValue() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);

//         VideoGamesSystemException exception = assertThrows(VideoGamesSystemException.class, () -> {
//             wishlistService.updateWishlistNbOfItems(wishlist.getId(), -1);
//         });
//         assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
//         assertEquals("Number of items cannot be negative.", exception.getMessage());
//     }

//     @Test
//     public void testGetWishlistByInvalidCustomer() {
//         Customer newCustomer = new Customer();
//         newCustomer.setUserName("Another User");
//         newCustomer.setEmail("another.user@gmail.com");
//         newCustomer.setPassword("password123");
//         newCustomer = customerRepository.save(newCustomer); // Save the new customer

//         // Attempt to retrieve a wishlist that doesn't exist for this customer
//         Wishlist retrievedWishlist = wishlistService.getWishlistByCustomer(newCustomer);
//         assertNull(retrievedWishlist, "Expected null as the customer has no wishlist.");
//     }

//     @Test
//     public void testExcessiveNbOfItemsInWishlist() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);
//         int largeNbOfItems = 1000;
//         Wishlist updatedWishlist = wishlistService.updateWishlistNbOfItems(wishlist.getId(), largeNbOfItems);

//         assertEquals(largeNbOfItems, updatedWishlist.getNbOfItems(), "NbOfItems should be updated without limits");
//     }

//     @Test
//     public void testCreateWishlistForMultipleCustomers() {
//         Wishlist wishlist1 = wishlistService.createWishlist(customer);
        
//         Customer anotherCustomer = new Customer();
//         anotherCustomer.setUserName("Second User");
//         anotherCustomer.setEmail("second.user@gmail.com");
//         anotherCustomer.setPassword("password123");
//         anotherCustomer = customerRepository.save(anotherCustomer);
        
//         Wishlist wishlist2 = wishlistService.createWishlist(anotherCustomer);

//         assertNotNull(wishlist1);
//         assertNotNull(wishlist2);
//         assertNotEquals(wishlist1.getId(), wishlist2.getId());
//     }

//     @Test
//     public void testClearWishlist() {
//         Wishlist wishlist = wishlistService.createWishlist(customer);
//         wishlistService.updateWishlistNbOfItems(wishlist.getId(), 5);
        
//         Wishlist clearedWishlist = wishlistService.updateWishlistNbOfItems(wishlist.getId(), 0); // Set to 0 to clear
//         assertEquals(0, clearedWishlist.getNbOfItems());
//     }
// }
