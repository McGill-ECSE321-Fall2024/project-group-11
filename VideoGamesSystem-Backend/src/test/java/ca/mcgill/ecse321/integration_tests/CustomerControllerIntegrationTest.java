package ca.mcgill.ecse321.integration_tests;

import ca.mcgill.ecse321.videogamessystem.VideogamessystemApplication;

import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VideogamessystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer savedCustomer;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        savedCustomer = new Customer();
        savedCustomer.setUserName("testUser");
        savedCustomer.setEmail("test@example.com");
        savedCustomer.setPassword("password123");
        savedCustomer.setPhoneNumber(123456789);
        savedCustomer.setAdress("123 Main St");
        customerRepository.save(savedCustomer);
    }

   // TODO : FIX  
@Test
public void testCreateCustomer() {
    CustomerRequestDto request = new CustomerRequestDto();
    request.setUserName("testUser");
    request.setEmail("test@example.com");
    request.setPassword("securePassword");
    request.setPhoneNumber(123456);
    request.setAdress("123 Main St");

    // Attempt to create customer and capture the response
    ResponseEntity<CustomerResponseDto> response = restTemplate.postForEntity("/customers", request, CustomerResponseDto.class);

    // Log response details if there’s an error
    if (response.getStatusCode() != HttpStatus.OK) {
        System.err.println("Failed to create customer. Status: " + response.getStatusCode());
        System.err.println("Response Body: " + response.getBody());
    }

    // Validate the response
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("testUser", response.getBody().getUserName());
}

    
    @Test
    public void testGetCustomerById() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        ResponseEntity<CustomerResponseDto> response = restTemplate.getForEntity(uri, CustomerResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        CustomerResponseDto retrievedCustomer = response.getBody();
        assertNotNull(retrievedCustomer);
        assertEquals(savedCustomer.getId(), retrievedCustomer.getId());
        assertEquals(savedCustomer.getUserName(), retrievedCustomer.getUserName());
    }

    @Test
    public void testGetCustomerByUserName() {
        URI uri = UriComponentsBuilder.fromPath("/customers/username/{userName}").buildAndExpand(savedCustomer.getUserName()).toUri();
        ResponseEntity<CustomerResponseDto> response = restTemplate.getForEntity(uri, CustomerResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedCustomer.getUserName(), response.getBody().getUserName());
    }

    @Test
    public void testGetCustomerByEmail() {
        URI uri = UriComponentsBuilder.fromPath("/customers/email/{email}").buildAndExpand(savedCustomer.getEmail()).toUri();
        ResponseEntity<CustomerResponseDto> response = restTemplate.getForEntity(uri, CustomerResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedCustomer.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void testGetCustomerByPhoneNumber() {
        URI uri = UriComponentsBuilder.fromPath("/customers/phone/{phoneNumber}").buildAndExpand(savedCustomer.getPhoneNumber()).toUri();
        ResponseEntity<CustomerResponseDto> response = restTemplate.getForEntity(uri, CustomerResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedCustomer.getPhoneNumber(), response.getBody().getPhoneNumber());
    }

    @Test
    public void testGetCustomersByAddress() {
        URI uri = UriComponentsBuilder.fromPath("/customers/address/{address}").buildAndExpand(savedCustomer.getAdress()).toUri();
        ResponseEntity<List> response = restTemplate.getForEntity(uri, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testUpdateCustomer() {
        CustomerRequestDto updateRequest = new CustomerRequestDto("updatedUser", "updated@example.com", "updatedPassword", 555555555, "789 Oak St", null);

        URI uri = UriComponentsBuilder.fromPath("/customers/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.put(uri, updateRequest);

        Customer updatedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals("updatedUser", updatedCustomer.getUserName());
        assertEquals("updated@example.com", updatedCustomer.getEmail());
        assertEquals(555555555, updatedCustomer.getPhoneNumber());
    }

    @Test
    public void testUpdateCustomerUserName() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}/username").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.put(uri + "?newUserName=newUsername", null);

        Customer updatedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals("newUsername", updatedCustomer.getUserName());
    }

    @Test
    public void testUpdateCustomerEmail() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}/email").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.put(uri + "?newEmail=new@example.com", null);

        Customer updatedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals("new@example.com", updatedCustomer.getEmail());
    }

    @Test
    public void testUpdateCustomerPhoneNumber() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}/phone").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.put(uri + "?newPhoneNumber=987654321", null);

        Customer updatedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals(987654321, updatedCustomer.getPhoneNumber());
    }

    @Test
    public void testUpdateCustomerAddress() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}/address").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.put(uri + "?newAddress=789 Oak St", null);

        Customer updatedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals("789 Oak St", updatedCustomer.getAdress());
    }

    @Test
    public void testDeleteCustomer() {
        URI uri = UriComponentsBuilder.fromPath("/customers/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        restTemplate.delete(uri);

        Customer deletedCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);
        assertNull(deletedCustomer);
    }

    @Test
    public void testGetAllCustomers() {
        ResponseEntity<List> response = restTemplate.getForEntity("/customers", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }
}
