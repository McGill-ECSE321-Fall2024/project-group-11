// package ca.mcgill.ecse321.integration_tests;

// import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewRequestDto;
// import ca.mcgill.ecse321.videogamessystem.dto.ReviewDto.ReviewResponseDto;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.ActiveProfiles;

// import java.sql.Date;
// import java.time.LocalDate;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("test")
// public class ReviewControllerIntegrationTest {

//     @Autowired
//     private TestRestTemplate restTemplate;

//     @Test
//     public void testCreateReview() {
//         ReviewRequestDto request = new ReviewRequestDto(
//                 "Great game!",
//                 5,
//                 Date.valueOf(LocalDate.now()),
//                 1L,  // Assuming customerId is 1
//                 "user123",
//                 null, // No parent review, set to null
//                 1L,  // Assuming gameId is 1
//                 "Game Title"
//         );

//         ResponseEntity<ReviewResponseDto> response = restTemplate.postForEntity("/reviews", request, ReviewResponseDto.class);
        
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals("Great game!", response.getBody().getReviewContent());
//         assertEquals(5, response.getBody().getGameRating());
//     }

//     @Test
//     public void testGetReviewById() {
//         Long reviewId = 1L;  // Replace with valid ID for existing review
//         ResponseEntity<ReviewResponseDto> response = restTemplate.getForEntity("/reviews/" + reviewId, ReviewResponseDto.class);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(reviewId, response.getBody().getId());
//     }

//     @Test
//     public void testGetReviewsByCustomer() {
//         Long customerId = 1L;  // Replace with valid customer ID
//         ResponseEntity<ReviewResponseDto[]> response = restTemplate.getForEntity("/reviews/customer/" + customerId, ReviewResponseDto[].class);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(customerId, response.getBody()[0].getCustomerId());
//     }
    
//     @Test
//     public void testUpdateReviewContent() {
//         Long reviewId = 1L;  // Replace with valid review ID
//         String newContent = "Updated review content";

//         restTemplate.put("/reviews/" + reviewId + "/content?newContent=" + newContent, null);

//         ResponseEntity<ReviewResponseDto> response = restTemplate.getForEntity("/reviews/" + reviewId, ReviewResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(newContent, response.getBody().getReviewContent());
//     }
// }
