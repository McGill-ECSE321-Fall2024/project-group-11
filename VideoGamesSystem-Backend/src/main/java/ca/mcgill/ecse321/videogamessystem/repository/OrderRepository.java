package ca.mcgill.ecse321.videogamessystem.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.videogamessystem.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    public Order findOrderByNumber(int number);
}