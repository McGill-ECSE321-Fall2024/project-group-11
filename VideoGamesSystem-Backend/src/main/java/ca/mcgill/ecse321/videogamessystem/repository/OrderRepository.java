package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Order;
import java.util.Date;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    
    Order findOrderByNumber(int number);

    List<Order> findOrderByOrderDate(Date orderDate);

    List<Order> findOrderByCustomer(Customer customer);

}