// package ca.mcgill.ecse321.videogamessystem.integration_tests;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestInstance.Lifecycle;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionRequestDto;
// import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionResponseDto;
// import ca.mcgill.ecse321.videogamessystem.repository.PromotionRepository;

// import java.sql.Date;
// import java.time.LocalDate;
// import java.util.List;

// @SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
// @TestMethodOrder(OrderAnnotation.class)
// @TestInstance(Lifecycle.PER_CLASS)
// public class PromotionControllerIntegrationTests {

//     @Autowired
//     private TestRestTemplate restTemplate;

//     @Autowired
//     private PromotionRepository promotionRepository;

//     private Long promotionId;

//     private static final int VALID_PERCENTAGE = 20;
//     private static final Date VALID_START_DATE = Date.valueOf(LocalDate.now().plusDays(1));
//     private static final Date VALID_END_DATE = Date.valueOf(LocalDate.now().plusDays(7));

//     private static final int INVALID_PERCENTAGE = 150;

//     @AfterAll
//     public void clearDatabase() {
//         promotionRepository.deleteAll();
//     }

//     @Test
//     @Order(1)
//     public void testCreatePromotion() {
//         // Arrange
//         PromotionRequestDto request = new PromotionRequestDto(VALID_PERCENTAGE, VALID_START_DATE, VALID_END_DATE);

//         // Act
//         ResponseEntity<PromotionResponseDto> response = restTemplate.postForEntity("/promotions", request, PromotionResponseDto.class);

//         // Assert
//         assertNotNull(response);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertNotNull(response.getBody().getId());
//         assertEquals(VALID_PERCENTAGE, response.getBody().getPercentage());
//         assertEquals(VALID_START_DATE, response.getBody().getStartDate());
//         assertEquals(VALID_END_DATE, response.getBody().getEndDate());
//         this.promotionId = response.getBody().getId();
//     }

//     @Test
//     @Order(2)
//     public void testGetPromotionById() {
//         // Arrange
//         String url = String.format("/promotions/%d", this.promotionId);

//         // Act
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity(url, PromotionResponseDto.class);

//         // Assert
//         assertNotNull(response);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(promotionId, response.getBody().getId());
//         assertEquals(VALID_PERCENTAGE, response.getBody().getPercentage());
//     }

//     @Test
//     @Order(3)
//     public void testGetAllPromotions() {
//         // Act
//         ResponseEntity<PromotionResponseDto[]> response = restTemplate.getForEntity("/promotions", PromotionResponseDto[].class);

//         // Assert
//         assertNotNull(response);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertTrue(response.getBody().length > 0, "The list of promotions should not be empty.");
//     }

//     @Test
//     @Order(4)
//     public void testIsPromotionAvailable() {
//         // Arrange
//         String url = String.format("/promotions/%d/available", this.promotionId);

//         // Act
//         ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);

//         // Assert
//         assertNotNull(response);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertFalse(response.getBody(), "The promotion should not be available yet.");
//     }

//     @Test
//     @Order(5)
//     public void testDeletePromotion() {
//         // Act
//         restTemplate.delete(String.format("/promotions/%d", this.promotionId));

//         // Assert
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity(String.format("/promotions/%d", this.promotionId), PromotionResponseDto.class);
//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }

//     @Test
//     @Order(6)
//     public void testCreatePromotionWithInvalidPercentage() {
//         // Arrange
//         PromotionRequestDto request = new PromotionRequestDto(INVALID_PERCENTAGE, VALID_START_DATE, VALID_END_DATE);

//         // Act
//         ResponseEntity<String> response = restTemplate.postForEntity("/promotions", request, String.class);

//         // Assert
//         assertNotNull(response);
//         assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//         assertTrue(response.getBody().contains("Percentage must be between 1 and 100"));
//     }
// }
