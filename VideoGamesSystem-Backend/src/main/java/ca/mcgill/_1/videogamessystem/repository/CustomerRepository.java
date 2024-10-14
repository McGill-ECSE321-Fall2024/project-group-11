package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    public Customer findCustomerById(int idNum);
}
