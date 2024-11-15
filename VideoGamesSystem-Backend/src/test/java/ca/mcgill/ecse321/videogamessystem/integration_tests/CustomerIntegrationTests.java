package ca.mcgill.ecse321.videogamessystem.integration_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

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
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerIntegrationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private CustomerRepository customerRepository;

	private static final String VALID_USERNAME = "Tim";
	private static final String VALID_EMAIL = "tim@yahoo.com";
	private static final String VALID_PASSWORD = "abcde";
	private static final int VALID_PHONENUMBER = 1234567;
	private static final String VALID_ADRESS= "123 Stanley Street";
	private Long customerId;

	@AfterAll
	public void clearDatabase() {
		customerRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void testCreateValidPerson() {
		// Arrange
		CustomerRequestDto request = new CustomerRequestDto(VALID_USERNAME, VALID_EMAIL, VALID_PASSWORD, VALID_PHONENUMBER, VALID_ADRESS);

		// Act
		ResponseEntity<CustomerResponseDto> response = restTemplate.postForEntity("/customers", request, CustomerResponseDto.class);

		// Assert
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getId() > 0, "The ID should be positive.");
		this.customerId = response.getBody().getId();
		assertEquals(VALID_USERNAME, response.getBody().getUserName());
		assertEquals(VALID_EMAIL, response.getBody().getEmail());
		assertEquals(VALID_PHONENUMBER, response.getBody().getPhoneNumber());
		assertEquals(VALID_ADRESS, response.getBody().getAdress());


	}
}

// 	@Test
// 	@Order(2)
// 	public void testGetValidPersonById() {
// 		// Arrange
// 		String url = String.format("/people/%d", this.personId);

// 		System.out.println(String.format("URL: %s", url));

// 		// Act
// 		ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);

// 		// Assert
// 		assertNotNull(response);
// 		assertEquals(HttpStatus.OK, response.getStatusCode());
// 		assertEquals(this.personId, response.getBody().getId());
// 		assertEquals(VALID_NAME, response.getBody().getName());
// 		assertEquals(VALID_EMAIL, response.getBody().getEmailAddress());
// 		LocalDate today = LocalDate.now();
// 		assertEquals(today, response.getBody().getCreationDate());
// 	}
// }