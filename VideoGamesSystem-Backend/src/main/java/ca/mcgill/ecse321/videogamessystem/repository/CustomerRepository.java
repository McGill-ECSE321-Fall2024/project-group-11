package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    public Customer findCustomerById(int idNum);
}


