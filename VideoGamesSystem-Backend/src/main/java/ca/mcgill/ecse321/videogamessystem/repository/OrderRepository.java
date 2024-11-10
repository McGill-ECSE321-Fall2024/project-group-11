package ca.mcgill.ecse321.videogamessystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Order;
import java.util.Date;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    // find the order with its id
    public Order findOrderByNumber(int number);

    List<Order> findOrderByOrderDate(Date orderDate);
}