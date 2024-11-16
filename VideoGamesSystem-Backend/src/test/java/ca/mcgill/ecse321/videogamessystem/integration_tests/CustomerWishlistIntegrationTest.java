// package ca.mcgill.ecse321.integration_tests;

// import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// import ca.mcgill.ecse321.videogamessystem.model.Customer;
// import ca.mcgill.ecse321.videogamessystem.model.Game;
// import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
// import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
// import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
// import ca.mcgill.ecse321.videogamessystem.service.CustomerService;
// import ca.mcgill.ecse321.videogamessystem.service.GameService;
// import ca.mcgill.ecse321.videogamessystem.service.WishlistService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// @SpringBootTest(classes = VideogamessystemApplication.class)
// @Transactional
// public class CustomerWishlistIntegrationTest {

//     @Autowired
//     private CustomerService customerService;

//     @Autowired
//     private GameService gameService;

//     @Autowired
//     private WishlistService wishlistService;

//     @Test
//     public void testCreateCustomerAndAddGameToWishlist() {
//         // Step 1: Create and save a Customer
//         Customer customer = customerService.createCustomer("johnDoe", "johndoe@example.com", "password123", 1234567, "123 Main St");
        
//         // Step 2: Create and save a Wishlist
//         Wishlist wishlist = new Wishlist();
//         wishlist.setCustomer(customer);  // Associate wishlist with customer
//         wishlist = wishlistService.createWishlist(customer);  // Save the wishlist

//         // Step 3: Create and save a Game
//         Game game = gameService.createGame("Exciting Adventure", 10, 59, "Epic Adventure Game", Category.Action, ConsoleType.PC);

//         // Step 4: Add the Game to the Wishlist
//         gameService.addGameToWishlist(game.getId(), wishlist.getId());

//         // Verify that the Wishlist now contains the Game
//         List<Game> wishlistGames = wishlistService.getGamesInWishlist(wishlist.getId());
//         assertNotNull(wishlistGames);
//         assertEquals(1, wishlistGames.size());
//         assertEquals("Exciting Adventure", wishlistGames.get(0).getTitle());


//     }
// }
