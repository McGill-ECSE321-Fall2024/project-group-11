package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
    //find the customer with its id
    public Customer findCustomerById(Long id);

    public Customer findCustomerByEmail(String email);

    public Customer findCustomerByuserName(String userName);
}
// add a find by usertname and find by email
// does not make sense to find by phine number or house adress