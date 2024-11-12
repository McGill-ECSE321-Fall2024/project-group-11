package ca.mcgill.ecse321.videogamessystem.service;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.videogamessystem.model.Order;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.repository.OrderRepository;
import jakarta.transaction.Transactional;
@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository= orderRepository;
    }

    @Transactional
    public Order createOrder(int number, Date orderDate, int cardNumber){

        Order orderToCreate = new Order();


        orderToCreate.setNumber(number);
        orderToCreate.setOrderDate(orderDate);
        orderToCreate.setCardNumber(cardNumber);

        return orderRepository.save(orderToCreate);

    }
    
    @Transactional
    public Order getOrderByNumber(int number){
        Order order= orderRepository.findOrderByNumber(number);

        if (order == null){
            throw new IllegalArgumentException("There is no order with that number.");
        }

        return order;
    }

    @Transactional
    public List<Order> getOrderByOrderDate(Date orderDate){
        List<Order> orders = orderRepository.findOrderByOrderDate(orderDate);

        if (orders == null){
            throw new IllegalArgumentException("There is no order with this Date.");
        }

        return orders;
    }

    @Transactional
    public List<Order> getOrderByCustomer(Customer customer){
        List<Order> orders = orderRepository.findOrderByCustomer(customer);
        if (orders == null){
            throw new IllegalArgumentException("There is no order for this customer.");
        }

        return orders;

    }
}