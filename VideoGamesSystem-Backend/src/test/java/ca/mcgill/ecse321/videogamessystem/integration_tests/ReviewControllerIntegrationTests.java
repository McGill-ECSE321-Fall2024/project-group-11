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
import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerResponseDto;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.GameDto.GameResponseDto;
import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewResponseDto;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.repository.GameRepository;
import ca.mcgill.ecse321.videogamessystem.repository.ReviewRepository;
import ca.mcgill.ecse321.videogamessystem.model.Game.Category;
import ca.mcgill.ecse321.videogamessystem.model.Game.ConsoleType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ReviewControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private static final String VALID_CUSTOMER_USERNAME = "testuser";
    private static final String VALID_CUSTOMER_EMAIL = "testuser@example.com";
    private static final String VALID_CUSTOMER_PASSWORD = "securepassword";
    private static final int VALID_CUSTOMER_PHONENUMBER = 123456;
    private static final String VALID_CUSTOMER_ADRESS = "123 Test Street";


    private static final String VALID_GAME_TITLE = "Test Game";
    private static final String VALID_GAME_DESCRIPTION = "This is a test game.";
    private static final int VALID_GAME_PRICE = 50;
    private static final Category VALID_GAME_CATEGORY = Category.Adventure;
    private static final ConsoleType VALID_GAME_CONSOLE_TYPE = ConsoleType.PS4;

    private static final String VALID_REVIEW_CONTENT = "Great game!";
    private static final int VALID_GAME_RATING = 5;
    private static final Date VALID_DATE = Date.valueOf(LocalDate.now());

    private Long customerId;
    private Long gameId;
    private Long reviewId;

    @AfterAll
    public void clearDatabase() {
        reviewRepository.deleteAll();
        gameRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testCreateCustomer() {
        // Arrange
        CustomerRequestDto request = new CustomerRequestDto(
                VALID_CUSTOMER_USERNAME,
                VALID_CUSTOMER_EMAIL,
                VALID_CUSTOMER_PASSWORD,
                VALID_CUSTOMER_PHONENUMBER,
                VALID_CUSTOMER_ADRESS
        );

        // Act
        ResponseEntity<CustomerResponseDto> response = restTemplate.postForEntity("/customers", request, CustomerResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0, "The ID should be positive.");
        this.customerId = response.getBody().getId();
        assertEquals(VALID_CUSTOMER_USERNAME, response.getBody().getUserName());
        assertEquals(VALID_CUSTOMER_EMAIL, response.getBody().getEmail());
    }

    @Test
    @Order(2)
    public void testCreateGame() {
        // Arrange
        GameRequestDto request = new GameRequestDto(
                VALID_GAME_DESCRIPTION,
                VALID_GAME_PRICE,
                VALID_GAME_TITLE,
                VALID_GAME_CATEGORY,
                VALID_GAME_CONSOLE_TYPE
        );

        // Act
        ResponseEntity<GameResponseDto> response = restTemplate.postForEntity("/games", request, GameResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0, "The ID should be positive.");
        this.gameId = response.getBody().getId();
        assertEquals(VALID_GAME_TITLE, response.getBody().getTitle());
        assertEquals(VALID_GAME_DESCRIPTION, response.getBody().getDescription());
    }

    @Test
    @Order(3)
    public void testCreateReview() {
        // Arrange
        ReviewRequestDto request = new ReviewRequestDto(
                VALID_REVIEW_CONTENT,
                VALID_GAME_RATING,
                VALID_DATE,
                this.customerId,
                // VALID_CUSTOMER_USERNAME,
                null,
                this.gameId
                // VALID_GAME_TITLE
        );

        // Act
        ResponseEntity<ReviewResponseDto> response = restTemplate.postForEntity("/reviews", request, ReviewResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0, "The ID should be positive.");
        this.reviewId = response.getBody().getId();
        assertEquals(VALID_REVIEW_CONTENT, response.getBody().getReviewContent());
        assertEquals(VALID_GAME_RATING, response.getBody().getGameRating());
        // assertEquals(VALID_DATE, response.getBody().getReviewDate());
    }

    @Test
    @Order(4)
    public void testGetReviewById() {
        // Arrange
        String url = String.format("/reviews/%d", this.reviewId);

        // Act
        ResponseEntity<ReviewResponseDto> response = restTemplate.getForEntity(url, ReviewResponseDto.class);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.reviewId, response.getBody().getId());
        assertEquals(VALID_REVIEW_CONTENT, response.getBody().getReviewContent());
    }

    @Test
    @Order(5)
    public void testDeleteReview() {
        // Act
        restTemplate.delete(String.format("/reviews/%d", this.reviewId));

        // Assert
        ResponseEntity<ReviewResponseDto> response = restTemplate.getForEntity(String.format("/reviews/%d", this.reviewId), ReviewResponseDto.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}