// package ca.mcgill.ecse321.videogamessystem.integration_tests;
// // package ca.mcgill.ecse321.integration_tests;

// // import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// // import org.springframework.boot.test.context.SpringBootTest;
// // import ca.mcgill.ecse321.videogamessystem.controller.GameController;
// // import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameRequestDto;
// // import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameResponseDto;
// // import ca.mcgill.ecse321.videogamessystem.model.Game;
// // import ca.mcgill.ecse321.videogamessystem.service.GameService;
// // import org.junit.jupiter.api.BeforeEach;
// // import org.junit.jupiter.api.Test;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// // import org.springframework.boot.test.context.SpringBootTest;
// // import org.springframework.http.MediaType;
// // import org.springframework.test.web.servlet.MockMvc;

// // import static org.hamcrest.Matchers.*;
// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // @SpringBootTest(classes = VideogamessystemApplication.class)
// // @AutoConfigureMockMvc
// // public class GameControllerIntegrationTest {

// //     @Autowired
// //     private MockMvc mockMvc;

// //     @Autowired
// //     private GameService gameService;

// //     private Long testGameId;

// //     @BeforeEach
// //     public void setup() {
// //         Game game = gameService.createGame("Test Description", 50, "Test Title", Game.Category.Action, Game.ConsoleType.PC);
// //         testGameId = game.getId();
// //     }

// //     @Test
// //     public void testCreateGame() throws Exception {
// //         GameRequestDto gameRequest = new GameRequestDto("New Game", 60, "Exciting Game", Game.Category.Sports, Game.ConsoleType.PS4);
        
// //         mockMvc.perform(post("/api/games")
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content("{ \"description\": \"New Game\", \"price\": 60, \"title\": \"Exciting Game\", \"category\": \"Sports\", \"consoleType\": \"PS5\" }"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.title").value("Exciting Game"));
// //     }

// //     @Test
// //     public void testGetGameById() throws Exception {
// //         mockMvc.perform(get("/api/games/" + testGameId))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.title").value("Test Title"));
// //     }

// //     @Test
// //     public void testGetAllGames() throws Exception {
// //         mockMvc.perform(get("/api/games"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$", hasSize(greaterThan(0))));
// //     }

// //     @Test
// //     public void testUpdateGame() throws Exception {
// //         mockMvc.perform(put("/api/games/" + testGameId)
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content("{ \"description\": \"Updated Game\", \"price\": 70, \"title\": \"Updated Title\", \"category\": \"Adventure\" }"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.title").value("Updated Title"));
// //     }

// //     @Test
// //     public void testDeleteGame() throws Exception {
// //         mockMvc.perform(delete("/api/games/" + testGameId))
// //                 .andExpect(status().isOk());
// //     }

// //     @Test
// //     public void testGetGamesByPrice() throws Exception {
// //         mockMvc.perform(get("/api/games/price/50"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].price").value(50));
// //     }

// //     @Test
// //     public void testGetGamesByTitle() throws Exception {
// //         mockMvc.perform(get("/api/games/title/Test Title"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].title").value("Test Title"));
// //     }

// //     @Test
// //     public void testGetGamesByCategory() throws Exception {
// //         mockMvc.perform(get("/api/games/category/Action"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].category").value("Action"));
// //     }

// //     @Test
// //     public void testGetGamesByConsoleType() throws Exception {
// //         mockMvc.perform(get("/api/games/console/PC"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].consoleType").value("PC"));
// //     }

// //     @Test
// //     public void testAddGameToWishlist() throws Exception {
// //         Long wishlistId = 1L; // Use an existing wishlist ID for this test
// //         mockMvc.perform(put("/api/games/" + testGameId + "/wishlist/" + wishlistId))
// //                 .andExpect(status().isOk());
// //     }

// //     @Test
// //     public void testGetGamesBetweenPrices() throws Exception {
// //         mockMvc.perform(get("/api/games/price-range").param("min", "40").param("max", "60"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].price", greaterThanOrEqualTo(40)))
// //                 .andExpect(jsonPath("$[0].price", lessThanOrEqualTo(60)));
// //     }

// //     @Test
// //     public void testGetGamesAbovePromotion() throws Exception {
// //         mockMvc.perform(get("/api/games/promotion/10"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].promotion", greaterThanOrEqualTo(10)));
// //     }
// // }
