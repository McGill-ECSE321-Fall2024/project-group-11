package ca.mcgill.ecse321.videogamessystem.service;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Wishlist;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public Customer createCustomer(String userName, String email, String password, int phoneNumber, String address) {
        // Validation logic can be added here (e.g., check if email or username is already taken)
        if (customerRepository.findCustomerByUserName(userName) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (customerRepository.findCustomerByEmail(email) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        Customer customer = new Customer(userName, email, password, phoneNumber, address);
        return customerRepository.save(customer);
    }

    // Retrieve customer by ID
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new IllegalArgumentException("Customer with ID " + id + " not found"));
    }

    // Retrieve customer by username
    public Customer getCustomerByUserName(String userName) {
        return customerRepository.findCustomerByUserName(userName);
    }

    // Retrieve customer by email
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    // Retrieve customer by phone number
    public Customer getCustomerByPhoneNumber(int phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    // Retrieve customers by address
    public List<Customer> getCustomersByAddress(String address) {
        return customerRepository.findCustomerByAdress(address);
    }

    // Update customer details
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

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }
}
