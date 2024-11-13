package ca.mcgill.ecse321.videogamessystem.service;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Review;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // regex for email validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    
    // Create a new customer
    @Transactional
    public Customer createCustomer(String userName, String email, String password, int phoneNumber, String address) {
        // Validation logic can be added here (e.g., check if email or username is already taken)
        //username check
        if (customerRepository.findCustomerByUserName(userName) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        //email check
        if (email == null || email.trim().length() == 0){
            throw new IllegalArgumentException("no empty email");
        }
        if (!Pattern.compile(emailRegex).matcher(email).matches()) {
            throw new IllegalArgumentException("invalid email");
        }
        if (customerRepository.findCustomerByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        //password check
        if (password == null || password.trim().length() < 4){
            
        }
        //phoneNumber check
        if (phoneNumber < 1111)
            throw new IllegalArgumentException("more digits is needed for phone number");
        if (customerRepository.findCustomerByPhoneNumber(phoneNumber) != null) {
            throw new IllegalArgumentException("phone number already exists");
        }

        //Address check
        if (address == null || address.trim().length() == 0) {
            throw new IllegalArgumentException("invalid email");
        }

        Customer customer = new Customer(userName, email, password, phoneNumber, address);
        return customerRepository.save(customer);
    }

    // Retrieve customer by ID
    @Transactional
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found"));
    }

    // Retrieve customer by username
    @Transactional
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.findCustomerByUserName(userName);
    }

    // Retrieve customer by email
    @Transactional
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    // Retrieve customer by phone number
    @Transactional
    public Customer getCustomerByPhoneNumber(int phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    // Retrieve customers by address
    @Transactional
    public List<Customer> getCustomersByAddress(String address) {
        return customerRepository.findCustomerByAdress(address);
    }

    // Update customer details
    @Transactional
    public Customer updateCustomer(Long id, String newUserName, String newEmail, int newPhoneNumber, String newAddress) {
        Customer customer = getCustomerById(id);
        
        if (newUserName != null && !newUserName.isEmpty()) {
            customer.setUserName(newUserName);
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            customer.setEmail(newEmail);
        }
        customer.setPhoneNumber(newPhoneNumber);
        customer.setAdress(newAddress);
        
        return customerRepository.save(customer);
    }

     // Update customer username
     @Transactional
     public Customer updateCustomerUserName(Long id, String newUserName) {
        Customer customer = getCustomerById(id);
        if (newUserName == null || newUserName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (customerRepository.findCustomerByUserName(newUserName) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        customer.setUserName(newUserName);
        return customerRepository.save(customer);
    }

    // Update customer email
    @Transactional
    public Customer updateCustomerEmail(Long id, String newEmail) {
        Customer customer = getCustomerById(id);
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (customerRepository.findCustomerByEmail(newEmail) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }

    // Update customer phone number
    @Transactional
    public Customer updateCustomerPhoneNumber(Long id, int newPhoneNumber) {
        Customer customer = getCustomerById(id);
        customer.setPhoneNumber(newPhoneNumber);
        return customerRepository.save(customer);
    }

    // Update customer address
    @Transactional
    public Customer updateCustomerAddress(Long id, String newAddress) {
        Customer customer = getCustomerById(id);
        customer.setAdress(newAddress);
        return customerRepository.save(customer);
    }

    // Delete a customer by ID
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    // Retrieve all customers
    @Transactional
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // get customer by review
    @Transactional
    public Customer getCustomerByReview(Review review) {
        Customer customer = review.getCustomer();
        if (customer == null){
            throw new RuntimeException("the review shouldn't exsist");
        }
        return customer;
    }

}
