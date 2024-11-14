// package ca.mcgill.ecse321.integration_tests;

// import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;
// import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameRequestDto;
// import ca.mcgill.ecse321.videogamessystem.dto.SpecificGameDto.SpecificGameResponseDto;
// import ca.mcgill.ecse321.videogamessystem.model.Game;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class SpecificGameControllerIntegrationTest {

//     @Autowired
//     private TestRestTemplate restTemplate;

//     private static final int SERIAL_NUMBER = 12345;
//     private static final Long GAME_ID = 1L; // Ensure this game ID exists in your test data
//     private static final String TITLE = "Sample Game";
//     private static final String DESCRIPTION = "A sample game for testing.";
//     private static final int PRICE = 50;
//     private static final Game.Category CATEGORY = Game.Category.Adventure;
//     private static final Game.ConsoleType CONSOLE_TYPE = Game.ConsoleType.PS4;

//     private SpecificGameRequestDto requestDto;

//     @BeforeEach
//     public void setup() {
//         // Create a SpecificGameRequestDto with all attributes for the tests
//         requestDto = new SpecificGameRequestDto(
//                 true,
//                 SERIAL_NUMBER,
//                 GAME_ID,
//                 TITLE,
//                 DESCRIPTION,
//                 PRICE,
//                 CATEGORY,
//                 CONSOLE_TYPE
//         );
//     }

//     @Test
//     public void testCreateSpecificGame() {
//         ResponseEntity<SpecificGameResponseDto> response = restTemplate.postForEntity("/specificGames", requestDto, SpecificGameResponseDto.class);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(SERIAL_NUMBER, response.getBody().getSerialNumber());
//         assertEquals(TITLE, response.getBody().getTitle());
//         assertEquals(DESCRIPTION, response.getBody().getDescription());
//         assertEquals(PRICE, response.getBody().getPrice());
//         assertEquals(CATEGORY, response.getBody().getCategory());
//         assertEquals(CONSOLE_TYPE, response.getBody().getConsoleType());
//     }

//     @Test
//     public void testGetSpecificGameBySerialNumber() {
//         // Ensure a specific game exists first
//         restTemplate.postForEntity("/specificGames", requestDto, SpecificGameResponseDto.class);

//         ResponseEntity<SpecificGameResponseDto> response = restTemplate.getForEntity("/specificGames/" + SERIAL_NUMBER, SpecificGameResponseDto.class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertEquals(SERIAL_NUMBER, response.getBody().getSerialNumber());
//         assertEquals(TITLE, response.getBody().getTitle());
//     }

//     @Test
//     public void testGetAllSpecificGames() {
//         // Create a sample specific game
//         restTemplate.postForEntity("/specificGames", requestDto, SpecificGameResponseDto.class);

//         ResponseEntity<SpecificGameResponseDto[]> response = restTemplate.getForEntity("/specificGames", SpecificGameResponseDto[].class);
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertTrue(response.getBody().length > 0);
//     }

//     @Test
//     public void testUpdateAvailability() {
//         // Ensure a specific game exists first
//         restTemplate.postForEntity("/specificGames", requestDto, SpecificGameResponseDto.class);

//         ResponseEntity<SpecificGameResponseDto> response = restTemplate.exchange(
//                 "/specificGames/" + SERIAL_NUMBER + "/availability?newAvailability=false",
//                 HttpMethod.PUT,
//                 null,
//                 SpecificGameResponseDto.class);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertNotNull(response.getBody());
//         assertFalse(response.getBody().isAvailability());
//     }

//     @Test
//     public void testDeleteSpecificGame() {
//         // Ensure a specific game exists first
//         restTemplate.postForEntity("/specificGames", requestDto, SpecificGameResponseDto.class);

//         restTemplate.delete("/specificGames/" + SERIAL_NUMBER);

//         ResponseEntity<SpecificGameResponseDto> response = restTemplate.getForEntity("/specificGames/" + SERIAL_NUMBER, SpecificGameResponseDto.class);
//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }
// }
