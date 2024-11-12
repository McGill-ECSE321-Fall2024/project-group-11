package ca.mcgill.ecse321.videogamessystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@SpringBootTest
public class CustomerTest {
	@Autowired
	private CustomerRepository customerRepository;

	@AfterEach
	public void clearDatabase() {
		customerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadCustomer() {
		// Create person
		String name = "Muffin Man";
		String emailAddress = "muffin.man@gmail.com";
		String password = "i_love_muffins";
		Customer muffinMan = new Customer(name, emailAddress, password, 01233443, "abc", 4);
		muffinMan.setUserName(name);
		muffinMan.setEmail(emailAddress);
		muffinMan.setPassword(password);

		// Save person
		muffinMan = customerRepository.save(muffinMan);
		long id = muffinMan.getId();

		// Read person from database
		Customer muffinManFromDb = customerRepository.findCustomerById(id);

		// Assert correct response
		assertNotNull(muffinMan);
		assertEquals(muffinManFromDb.getUserName(), name);
		assertEquals(muffinManFromDb.getEmail(), emailAddress);
		assertEquals(muffinManFromDb.getPassword(), password);
	}
}