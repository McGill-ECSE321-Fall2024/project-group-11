// package ca.mcgill.ecse321.videogamessystem.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.model.Customer;

@SpringBootTest
public class CustomerRepositoryTests {
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
		Person muffinMan = new Customer();
		muffinMan.setName(name);
		muffinMan.setEmailAddress(emailAddress);
		muffinMan.setPassword(password);

		// Save person
		muffinMan = customerRepository.save(person);
		long id = muffinMan.getId();

		// Read person from database
		Customer muffinManFromDb = customerRepository.findPersonById(id);

		// Assert correct response
		assertNotNull(customer);
		assertEquals(muffinManFromDb.getName(), name);
		assertEquals(muffinManFromDb.getEmailAddress(), emailAddress);
		assertEquals(muffinManFromDb.getPassword(), password);
	}
}