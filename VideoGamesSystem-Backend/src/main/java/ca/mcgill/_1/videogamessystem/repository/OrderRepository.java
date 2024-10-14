package ca.mcgill._1.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    public Order findOrderById(int id);
}
