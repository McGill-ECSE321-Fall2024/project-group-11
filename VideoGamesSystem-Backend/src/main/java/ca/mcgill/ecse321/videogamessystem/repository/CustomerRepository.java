package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    public Customer findCustomerById(Long id);
}