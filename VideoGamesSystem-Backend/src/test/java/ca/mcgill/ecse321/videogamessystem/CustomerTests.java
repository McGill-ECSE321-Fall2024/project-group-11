package test.java.ca.mcgill.ecse321.videogamessystem;
// package ca.mcgill.ecse321.videogamessystem.repository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.dao.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import ca.mcgill.ecse321.videogamessystem.model.Customer;

@SpringBootTest
public class CustomerTests {
    @Autowired 
    private CustomerRepository customerRepo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        customerRepo.deleteAll();
    }

    @Test
    public void testCreateAndReadCustomer(){
        // Create
        String aUserName = "Gommo203";
        long millis = System.currentTimeMillis();
        Date aCreationDate = new Date(millis);
        String aAddress = "123 McGill Street";
        int aPhoneNumber = 123456678;
        String aEmail = "paolo.lahoud@mailmcgill.ca";
        String aPassword = "987654321";


        Customer customer = new Customer(aUserName, aEmail, aPassword, aPhoneNumber, aAddress, aCreationDate);

        //Save
        customer = customerRepo.save(customer);
        //wishlist = wishlistRepo.save(wishlist);

        int id = customer.getId();
        //Read
        Customer customer_from_DB = customerRepo.findCustomerById(id);


        //Assert
        assertNotNull(customer_from_DB);
        assertEquals(customer_from_DB.getUserName(), customer.getUserName());
        assertEquals(customer_from_DB.getAdress(), customer.getAdress());
        assertEquals(customer_from_DB.getPhoneNumber(), customer.getPhoneNumber());
        assertEquals(customer_from_DB.getEmail(), customer.getEmail());
        assertEquals(customer_from_DB.getPassword(), customer.getPassword());
    }
}