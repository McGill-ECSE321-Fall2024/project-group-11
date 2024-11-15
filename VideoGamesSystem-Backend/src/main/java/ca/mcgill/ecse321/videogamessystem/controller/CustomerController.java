package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.CustomerDto.CustomerResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Create a new customer.
     * 
     * @param request the customer request DTO containing the customer details
     * @return the response DTO with the created customer details
     */
    @PostMapping("/")
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto request) {

        Customer customer = customerService.createCustomer(
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getAdress()
        );
        return new CustomerResponseDto(customer);
    }

    /**
     * Retrieve a customer by their ID.
     * 
     * @param id the ID of the customer
     * @return the response DTO with the customer details
     */
    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new CustomerResponseDto(customer);
    }

    /**
     * Retrieve a customer by their username.
     * 
     * @param userName the username of the customer
     * @return the response DTO with the customer details
     */
    @GetMapping("/username/{userName}")
    public CustomerResponseDto getCustomerByUserName(@PathVariable String userName) {
        Customer customer = customerService.getCustomerByUserName(userName);
        return new CustomerResponseDto(customer);
    }

    /**
     * Retrieve a customer by their email.
     * 
     * @param email the email of the customer
     * @return the response DTO with the customer details
     */
    @GetMapping("/email/{email}")
    public CustomerResponseDto getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return new CustomerResponseDto(customer);
    }

    /**
     * Retrieve a customer by their phone number.
     * 
     * @param phoneNumber the phone number of the customer
     * @return the response DTO with the customer details
     */
    @GetMapping("/phone/{phoneNumber}")
    public CustomerResponseDto getCustomerByPhoneNumber(@PathVariable int phoneNumber) {
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return new CustomerResponseDto(customer);
    }

    /**
     * Retrieve customers by address.
     * 
     * @param address the address of the customers
     * @return a list of response DTOs with the details of each customer found
     */
    @GetMapping("/address/{address}")
    public List<CustomerResponseDto> getCustomersByAddress(@PathVariable String address) {
        List<Customer> customers = customerService.getCustomersByAddress(address);
        return customers.stream().map(CustomerResponseDto::new).collect(Collectors.toList());
    }

    /**
     * Update a customer's details.
     * 
     * @param id the ID of the customer to update
     * @param request the customer request DTO containing the updated details
     * @return the response DTO with the updated customer details
     */
    @PutMapping("/{id}")
    public CustomerResponseDto updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto request) {
        Customer updatedCustomer = customerService.updateCustomer(
                id,
                request.getUserName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getAdress()
        );
        return new CustomerResponseDto(updatedCustomer);
    }

    /**
     * Update a customer's username.
     * 
     * @param id the ID of the customer
     * @param newUserName the new username for the customer
     * @return the response DTO with the updated customer details
     */
    @PutMapping("/{id}/username")
    public CustomerResponseDto updateCustomerUserName(@PathVariable Long id, @RequestParam String newUserName) {
        Customer updatedCustomer = customerService.updateCustomerUserName(id, newUserName);
        return new CustomerResponseDto(updatedCustomer);
    }

    /**
     * Update a customer's email.
     * 
     * @param id the ID of the customer
     * @param newEmail the new email for the customer
     * @return the response DTO with the updated customer details
     */
    @PutMapping("/{id}/email")
    public CustomerResponseDto updateCustomerEmail(@PathVariable Long id, @RequestParam String newEmail) {
        Customer updatedCustomer = customerService.updateCustomerEmail(id, newEmail);
        return new CustomerResponseDto(updatedCustomer);
    }

    /**
     * Update a customer's phone number.
     * 
     * @param id the ID of the customer
     * @param newPhoneNumber the new phone number for the customer
     * @return the response DTO with the updated customer details
     */
    @PutMapping("/{id}/phone")
    public CustomerResponseDto updateCustomerPhoneNumber(@PathVariable Long id, @RequestParam int newPhoneNumber) {
        Customer updatedCustomer = customerService.updateCustomerPhoneNumber(id, newPhoneNumber);
        return new CustomerResponseDto(updatedCustomer);
    }

    /**
     * Update a customer's address.
     * 
     * @param id the ID of the customer
     * @param newAddress the new address for the customer
     * @return the response DTO with the updated customer details
     */
    @PutMapping("/{id}/address")
    public CustomerResponseDto updateCustomerAddress(@PathVariable Long id, @RequestParam String newAddress) {
        Customer updatedCustomer = customerService.updateCustomerAddress(id, newAddress);
        return new CustomerResponseDto(updatedCustomer);
    }

    /**
     * Delete a customer by their ID.
     * 
     * @param id the ID of the customer to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    /**
     * Retrieve all customers.
     * 
     * @return a list of response DTOs with the details of each customer
     */
    @GetMapping
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(CustomerResponseDto::new).collect(Collectors.toList());
    }
}
