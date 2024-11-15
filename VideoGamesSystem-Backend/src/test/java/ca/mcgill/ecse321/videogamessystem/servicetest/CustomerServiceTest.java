package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.exception.VideoGamesSystemException;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.repository.WishlistRepository;
import ca.mcgill.ecse321.videogamessystem.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer_Success() {
        // Arrange
        String userName = "testUser";
        String email = "test@example.com";
        String password = "password";
        int phoneNumber = 1234567890;
        String address = "123 Test St";
        
        when(customerRepository.findCustomerByUserName(userName)).thenReturn(null);
        when(customerRepository.findCustomerByEmail(email)).thenReturn(null);
        when(customerRepository.findCustomerByPhoneNumber(phoneNumber)).thenReturn(null);
        
        Customer customer = new Customer(userName, email, password, phoneNumber, address);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        Customer createdCustomer = customerService.createCustomer(userName, email, password, phoneNumber, address);

        // Assert
        assertNotNull(createdCustomer);
        assertEquals(userName, createdCustomer.getUserName());
        assertEquals(email, createdCustomer.getEmail());
        assertEquals(phoneNumber, createdCustomer.getPhoneNumber());
        assertEquals(address, createdCustomer.getAddress());
    }

    @Test
    public void testGetCustomerById_CustomerExists() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Customer foundCustomer = customerService.getCustomerById(customerId);

        // Assert
        assertNotNull(foundCustomer);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerById_CustomerDoesNotExist() {
        // Arrange
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(VideoGamesSystemException.class, () -> {
            customerService.getCustomerById(customerId);
        });
        
        assertEquals("Customer with ID 1 not found", exception.getMessage());
    }

    @Test
    public void testUpdateCustomer_Success() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer("oldUser", "old@example.com", "password", 1234, "Old Address");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
    
        // Mock save to return the updated customer
        when(customerRepository.save(customer)).thenReturn(customer);
    
        // Act
        Customer updatedCustomer = customerService.updateCustomer(customerId, "newUser", "new@example.com", 5678, "New Address");
    
        // Assert
        assertNotNull(updatedCustomer);
        assertEquals("newUser", updatedCustomer.getUserName());
        assertEquals("new@example.com", updatedCustomer.getEmail());
        assertEquals(5678, updatedCustomer.getPhoneNumber());
        assertEquals("New Address", updatedCustomer.getAddress());
        verify(customerRepository, times(1)).save(customer);
    }
    
    @Test
    public void testDeleteCustomer_Success() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).delete(customer);
    }
}
