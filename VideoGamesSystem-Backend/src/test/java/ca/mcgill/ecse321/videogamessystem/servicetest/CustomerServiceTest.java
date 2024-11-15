package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
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
        assertEquals(address, createdCustomer.getAdress());
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
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
        assertEquals("New Address", updatedCustomer.getAdress());
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
    @Test
    public void testUpdateCustomerUserName_Success() {
        // Arrange
        Long customerId = 1L;
        String newUserName = "updatedUser";
        Customer customer = new Customer("oldUser", "old@example.com", "password", 1234, "Old Address");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer updatedCustomer = customerService.updateCustomerUserName(customerId, newUserName);

        // Assert
        assertNotNull(updatedCustomer);
        assertEquals(newUserName, updatedCustomer.getUserName());
        verify(customerRepository, times(1)).save(customer);
    }
    @Test
    public void testUpdateCustomerEmail_Success() {
        // Arrange
        Long customerId = 1L;
        String newEmail = "updated@example.com";
        Customer customer = new Customer("testUser", "old@example.com", "password", 1234, "123 Test St");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer updatedCustomer = customerService.updateCustomerEmail(customerId, newEmail);

        // Assert
        assertNotNull(updatedCustomer);
        assertEquals(newEmail, updatedCustomer.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }
    @Test
    public void testCreateCustomer_InvalidEmail_Empty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "", "password", 1234567890, "123 Test St");
        });
        assertEquals("no empty email", exception.getMessage());
    }

    @Test
    public void testCreateCustomer_InvalidEmail_Format() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "invalid-email", "password", 1234567890, "123 Test St");
        });
        assertEquals("invalid email", exception.getMessage());
    }
    
    @Test
    public void testCreateCustomer_DuplicateEmail() {
        String email = "test@example.com";
        when(customerRepository.findCustomerByEmail(email)).thenReturn(new Customer());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", email, "password", 1234567890, "123 Test St");
        });
        assertEquals("Email already exists", exception.getMessage());
    }
    @Test
    public void testCreateCustomer_PasswordTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "test@example.com", "abc", 1234567890, "123 Test St");
        });
        assertEquals("password must be more than 4 characters", exception.getMessage());
    }
    @Test
    public void testCreateCustomer_InvalidPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "test@example.com", "password", 123, "123 Test St");
        });
        assertEquals("more digits is needed for phone number", exception.getMessage());
    }
    @Test
    public void testCreateCustomer_DuplicatePhoneNumber() {
        int phoneNumber = 1234567890;
        when(customerRepository.findCustomerByPhoneNumber(phoneNumber)).thenReturn(new Customer());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "test@example.com", "password", phoneNumber, "123 Test St");
        });
        assertEquals("phone number already exists", exception.getMessage());
    }
    @Test
    public void testCreateCustomer_InvalidAddress() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer("testUser", "test@example.com", "password", 1234567890, "");
        });
        assertEquals("invalid address", exception.getMessage());
    }
    @Test
    public void testCreateCustomer_DuplicateUserName() {
        String userName = "testUser";
        when(customerRepository.findCustomerByUserName(userName)).thenReturn(new Customer());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(userName, "test@example.com", "password", 1234567890, "123 Test St");
        });
        assertEquals("Username already exists", exception.getMessage());
    }
    @Test
    public void testGetCustomerByUserName_Success() {
        // Arrange
        String userName = "existingUser";
        Customer mockCustomer = new Customer(userName, "existing@example.com", "password", 1234567890, "123 Existing St");
        when(customerRepository.findCustomerByUserName(userName)).thenReturn(mockCustomer);

        // Act
        Customer foundCustomer = customerService.getCustomerByUserName(userName);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(userName, foundCustomer.getUserName());
        assertEquals("existing@example.com", foundCustomer.getEmail());
        verify(customerRepository, times(1)).findCustomerByUserName(userName);
    }
    @Test
    public void testGetCustomerByEmail_Success() {
        // Arrange
        String email = "existing@example.com";
        Customer mockCustomer = new Customer("existingUser", email, "password", 1234567890, "123 Existing St");
        when(customerRepository.findCustomerByEmail(email)).thenReturn(mockCustomer);

        // Act
        Customer foundCustomer = customerService.getCustomerByEmail(email);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(email, foundCustomer.getEmail());
        assertEquals("existingUser", foundCustomer.getUserName());
        verify(customerRepository, times(1)).findCustomerByEmail(email);
    }
    @Test
    public void testGetCustomerByPhoneNumber_Success() {
        // Arrange
        int phoneNumber = 1234567890;
        Customer mockCustomer = new Customer("existingUser", "existing@example.com", "password", phoneNumber, "123 Existing St");
        when(customerRepository.findCustomerByPhoneNumber(phoneNumber)).thenReturn(mockCustomer);

        // Act
        Customer foundCustomer = customerService.getCustomerByPhoneNumber(phoneNumber);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(phoneNumber, foundCustomer.getPhoneNumber());
        assertEquals("existingUser", foundCustomer.getUserName());
        verify(customerRepository, times(1)).findCustomerByPhoneNumber(phoneNumber);
    }
}
