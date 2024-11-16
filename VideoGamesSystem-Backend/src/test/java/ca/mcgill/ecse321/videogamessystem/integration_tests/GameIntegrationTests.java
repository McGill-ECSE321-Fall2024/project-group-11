package ca.mcgill.ecse321.videogamessystem.integration_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;

import java.util.List;

@SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class GameIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    private static final String VALID_TITLE = "Test Game";
    private static final String VALID_DESCRIPTION = "Test game description.";
    private static final int VALID_PRICE = 50;
    private static final Category VALID_CATEGORY = Category.Adventure;
    private static final ConsoleType VALID_CONSOLE_TYPE = ConsoleType.PS4;

    private static final String UPDATED_TITLE = "Updated Game";
    private static final int UPDATED_PRICE = 60;

    private Long gameId;

    @AfterAll
    public void clearDatabase() {
        gameRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testCreateGame() {
        // Arrange
        GameRequestDto request = new GameRequestDto(VALID_DESCRIPTION, VALID_PRICE, VALID_TITLE, VALID_CATEGORY, VALID_CONSOLE_TYPE);

        // Act
        ResponseEntity<GameResponseDto> response = restTemplate.postForEntity("/games", request, GameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0, "The ID should be positive.");
        this.gameId = response.getBody().getId();
        assertEquals(VALID_TITLE, response.getBody().getTitle());
        assertEquals(VALID_DESCRIPTION, response.getBody().getDescription());
        assertEquals(VALID_PRICE, response.getBody().getPrice());
        assertEquals(VALID_CATEGORY, response.getBody().getCategory());
        assertEquals(VALID_CONSOLE_TYPE, response.getBody().getConsoleType());
    }

    @Test
    @Order(2)
    public void testGetGameById() {
        // Arrange
        String url = String.format("/games/%d", this.gameId);

        // Act
        ResponseEntity<GameResponseDto> response = restTemplate.getForEntity(url, GameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.gameId, response.getBody().getId());
        assertEquals(VALID_TITLE, response.getBody().getTitle());
        assertEquals(VALID_DESCRIPTION, response.getBody().getDescription());
        assertEquals(VALID_PRICE, response.getBody().getPrice());
    }

    @Test
    @Order(3)
    public void testGetAllGames() {
        // Act
        ResponseEntity<GameResponseDto[]> response = restTemplate.getForEntity("/games", GameResponseDto[].class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0, "The list of games should not be empty.");
    }

    @Test
    @Order(4)
    public void testUpdateGameTitleAndPrice() {
        // Arrange
        String updateTitleUrl = String.format("/games/%d/title?newTitle=%s", this.gameId, UPDATED_TITLE);
        restTemplate.put(updateTitleUrl, null);

        String updatePriceUrl = String.format("/games/%d/price?newPrice=%d", this.gameId, UPDATED_PRICE);
        restTemplate.put(updatePriceUrl, null);

        // Act
        ResponseEntity<GameResponseDto> response = restTemplate.getForEntity(String.format("/games/%d", this.gameId), GameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(UPDATED_TITLE, response.getBody().getTitle());
        assertEquals(UPDATED_PRICE, response.getBody().getPrice());
    }

    @Test
    @Order(5)
    public void testDeleteGame() {
        // Act
        restTemplate.delete(String.format("/games/%d", this.gameId));

        // Assert
        ResponseEntity<GameResponseDto> response = restTemplate.getForEntity(String.format("/games/%d", this.gameId), GameResponseDto.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
