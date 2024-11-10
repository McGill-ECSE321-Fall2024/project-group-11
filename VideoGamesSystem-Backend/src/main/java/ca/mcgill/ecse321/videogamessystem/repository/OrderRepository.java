package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Order;
import java.util.List;
import java.util.Date;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    public Order findOrderByNumber(int number);

    List<Order> findOrderByOrderDate(Date orderDate);
}