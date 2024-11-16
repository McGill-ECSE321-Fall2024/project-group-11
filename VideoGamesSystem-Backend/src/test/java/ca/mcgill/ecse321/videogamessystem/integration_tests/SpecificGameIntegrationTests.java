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
import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificGameRepository;

@SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SpecificGameIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SpecificGameRepository specificGameRepository;

    private static final String VALID_TITLE = "Test Game";
    private static final String VALID_DESCRIPTION = "Test game description.";
    private static final int VALID_PRICE = 50;
    private static final Category VALID_CATEGORY = Category.Adventure;
    private static final ConsoleType VALID_CONSOLE_TYPE = ConsoleType.PS4;

    private static final int SPECIFIC_GAME_SERIAL_NUMBER = 12345;
    private static final boolean SPECIFIC_GAME_AVAILABILITY = true;

    private Long gameId;

    @AfterAll
    public void clearDatabase() {
        specificGameRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testCreateGame() {
        // Arrange
        GameRequestDto gameRequest = new GameRequestDto(
                VALID_DESCRIPTION,
                VALID_PRICE,
                VALID_TITLE,
                VALID_CATEGORY,
                VALID_CONSOLE_TYPE
        );

        // Act
        ResponseEntity<GameResponseDto> response = restTemplate.postForEntity("/games", gameRequest, GameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        this.gameId = response.getBody().getId();
        assertNotNull(gameId);
        assertEquals(VALID_TITLE, response.getBody().getTitle());
    }

    @Test
    @Order(2)
    public void testCreateSpecificGame() {
        // Arrange
        SpecificGameRequestDto specificGameRequest = new SpecificGameRequestDto(
                SPECIFIC_GAME_AVAILABILITY,
                SPECIFIC_GAME_SERIAL_NUMBER,
                gameId
        );

        // Act
        ResponseEntity<SpecificGameResponseDto> response = restTemplate.postForEntity("/specificGames", specificGameRequest, SpecificGameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(SPECIFIC_GAME_SERIAL_NUMBER, response.getBody().getSerialNumber());
        assertEquals(SPECIFIC_GAME_AVAILABILITY, response.getBody().isAvailability());
        assertEquals(gameId, response.getBody().getGame().getId());
    }

    @Test
    @Order(3)
    public void testGetSpecificGameBySerialNumber() {
        // Act
        ResponseEntity<SpecificGameResponseDto> response = restTemplate.getForEntity(
                "/specificGames/" + SPECIFIC_GAME_SERIAL_NUMBER,
                SpecificGameResponseDto.class
        );

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(SPECIFIC_GAME_SERIAL_NUMBER, response.getBody().getSerialNumber());
        assertEquals(SPECIFIC_GAME_AVAILABILITY, response.getBody().isAvailability());
    }

    @Test
    @Order(4)
    public void testDeleteSpecificGame() {
        // Act
        restTemplate.delete("/specificGames/" + SPECIFIC_GAME_SERIAL_NUMBER);

        // Verify deletion
        ResponseEntity<SpecificGameResponseDto> response = restTemplate.getForEntity(
                "/specificGames/" + SPECIFIC_GAME_SERIAL_NUMBER,
                SpecificGameResponseDto.class
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
