package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    Customer findCustomerById(Long id);

    Customer findCustomerByUserName(String userName);

    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhoneNumber(int phoneNumber);

    List<Customer> findCustomerByAddress(String adress);
}