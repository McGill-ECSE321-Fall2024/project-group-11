// package ca.mcgill.ecse321.integration_tests;

// import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionRequestDto;
// import ca.mcgill.ecse321.videogamessystem.dto.PromotionDto.PromotionResponseDto;
// import ca.mcgill.ecse321.videogamessystem.model.Promotion;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import java.sql.Date;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class PromotionControllerIntegrationTest {

//     @Autowired
//     private TestRestTemplate restTemplate;

//     private PromotionRequestDto promotionRequest;

//     @BeforeEach
//     public void setup() {
//         promotionRequest = new PromotionRequestDto();
//         promotionRequest.setPercentage(20);
//         promotionRequest.setStartDate(Date.valueOf("2024-01-01"));
//         promotionRequest.setEndDate(Date.valueOf("2024-12-31"));
//     }

//     @Test
//     public void testCreatePromotion() {
//         ResponseEntity<PromotionResponseDto> response = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(promotionRequest.getPercentage(), response.getBody().getPercentage());
//     }

//     @Test
//     public void testGetPromotionById() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId(), PromotionResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(createdPromotion.getId(), response.getBody().getId());
//     }

//     @Test
//     public void testGetAllPromotions() {
//         restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class);
//         ResponseEntity<List> response = restTemplate.getForEntity("/promotions", List.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertFalse(response.getBody().isEmpty());
//     }

//     @Test
//     public void testGetPromotionsByStartDate() {
//         restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class);
//         ResponseEntity<List> response = restTemplate.getForEntity("/promotions/startDate/2024-01-01", List.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertFalse(response.getBody().isEmpty());
//     }

//     @Test
//     public void testGetPromotionsByEndDate() {
//         restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class);
//         ResponseEntity<List> response = restTemplate.getForEntity("/promotions/endDate/2024-12-31", List.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertFalse(response.getBody().isEmpty());
//     }

//     @Test
//     public void testGetPromotionsByPercentage() {
//         restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class);
//         ResponseEntity<List> response = restTemplate.getForEntity("/promotions/percentage/20", List.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertFalse(response.getBody().isEmpty());
//     }

//     @Test
//     public void testUpdatePromotionPercentage() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         restTemplate.put("/promotions/" + createdPromotion.getId() + "/percentage?percentage=25", null);
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId(), PromotionResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(25, response.getBody().getPercentage());
//     }

//     @Test
//     public void testUpdatePromotionStartDate() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         restTemplate.put("/promotions/" + createdPromotion.getId() + "/startDate?startDate=2024-02-01", null);
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId(), PromotionResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(Date.valueOf("2024-02-01"), response.getBody().getStartDate());
//     }

//     @Test
//     public void testUpdatePromotionEndDate() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         restTemplate.put("/promotions/" + createdPromotion.getId() + "/endDate?endDate=2024-11-30", null);
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId(), PromotionResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(Date.valueOf("2024-11-30"), response.getBody().getEndDate());
//     }

//     @Test
//     public void testDeletePromotion() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         restTemplate.delete("/promotions/" + createdPromotion.getId());
//         ResponseEntity<PromotionResponseDto> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId(), PromotionResponseDto.class);
//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }

//     @Test
//     public void testIsPromotionAvailable() {
//         PromotionResponseDto createdPromotion = restTemplate.postForEntity("/promotions", promotionRequest, PromotionResponseDto.class).getBody();
//         ResponseEntity<Boolean> response = restTemplate.getForEntity("/promotions/" + createdPromotion.getId() + "/available", Boolean.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertTrue(response.getBody());
//     }
// }
