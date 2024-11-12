package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.Order;
import ca.mcgill.ecse321.videogamessystem.repository.OrderRepository;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order createOrder(Date orderDate, int cardNumber, Long customerId) {
        if (orderDate == null || orderDate.after(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }
        if (cardNumber <= 0) {
            throw new IllegalArgumentException("Card number must be valid.");
        }

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setCardNumber(cardNumber);
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrderById(int number) {
        Order order = orderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        return order;
    }

    @Transactional
    public List<Order> getOrdersByOrderDate(Date orderDate) {
        return orderRepository.findOrderByOrderDate(orderDate);
    }

    @Transactional
    public List<Order> getOrdersByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        return orderRepository.findOrderByCustomer(customer);
    }

    @Transactional
    public Order updateOrderDate(int number, Date newOrderDate) {
        Order order = orderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (newOrderDate == null || newOrderDate.after(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }

        order.setOrderDate(newOrderDate);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateCardNumber(int number, int newCardNumber) {
        Order order = orderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (newCardNumber <= 0) {
            throw new IllegalArgumentException("Card number must be valid.");
        }

        order.setCardNumber(newCardNumber);
        return orderRepository.save(order);
    }

    @Transactional
    public Order deleteOrder(int number) {
        Order order = orderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }

        orderRepository.delete(order);
        return order;
    }

    @Transactional
    public List<Order> getAllOrders() {
        return toList(orderRepository.findAll());
    }

    /**
     * Converts an {@code Iterable} to a {@code List}.
     * @param iterable the {@code Iterable} to convert
     * @param <T>      the type of elements in the iterable
     * @return a {@code List} containing the elements of the {@code Iterable}
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}

