package ca.mcgill.ecse321.videogamessystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificOrderRepository;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;

@Service
public class SpecificOrderService {

    @Autowired
    private SpecificOrderRepository specificOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @param orderDate
     * @param cardNumber
     * @param customerId
     * @return
     */
    @Transactional
    public SpecificOrder createSpecificOrder(Date orderDate, int cardNumber, Long customerId) {
        if (orderDate == null || orderDate.after(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }
        if (cardNumber <= 0) {
            throw new IllegalArgumentException("Card number must be valid.");
        }

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        
        SpecificOrder specificOrder = new SpecificOrder();
        specificOrder.setOrderDate(orderDate);
        specificOrder.setCardNumber(cardNumber);
        specificOrder.setCustomer(customer);
        return specificOrderRepository.save(specificOrder);
    }

    /**
     * @param number
     * @return
     */
    @Transactional
    public SpecificOrder getOrderById(int number) {
        SpecificOrder order = specificOrderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        return order;
    }

    /**
     * @param orderDate
     * @return
     */
    @Transactional
    public List<SpecificOrder> getOrdersByOrderDate(Date orderDate) {
        return specificOrderRepository.findOrderByOrderDate(orderDate);
    }

    /**
     * @param customerId
     * @return
     */
    @Transactional
    public List<SpecificOrder> getOrdersByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> 
            new IllegalArgumentException("Customer not found."));
        return specificOrderRepository.findOrderByCustomer(customer);
    }

    /**
     * @param number
     * @param newOrderDate
     * @return
     */
    @Transactional
    public SpecificOrder updateOrderDate(int number, Date newOrderDate) {
        SpecificOrder order = specificOrderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (newOrderDate == null || newOrderDate.after(new Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Order date cannot be in the future.");
        }

        order.setOrderDate(newOrderDate);
        return specificOrderRepository.save(order);
    }

    /**
     * @param number
     * @param newCardNumber
     * @return
     */
    @Transactional
    public SpecificOrder updateCardNumber(int number, int newCardNumber) {
        SpecificOrder order = specificOrderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }
        if (newCardNumber <= 0) {
            throw new IllegalArgumentException("Card number must be valid.");
        }

        order.setCardNumber(newCardNumber);
        return specificOrderRepository.save(order);
    }

    /**
     * @param number
     * @return
     */
    @Transactional
    public SpecificOrder deleteOrder(int number) {
        SpecificOrder order = specificOrderRepository.findOrderByNumber(number);
        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }

        specificOrderRepository.delete(order);
        return order;
    }

    /**
     * @return
     */
    @Transactional
    public List<SpecificOrder> getAllOrders() {
        return toList(specificOrderRepository.findAll());
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

    // assign order to customer
    /**
     * @param orderID
     * @param customer
     * @return
     */
    @Transactional
    public SpecificOrder placeNewOrder(int orderID, Customer customer){
        SpecificOrder order = specificOrderRepository.findOrderByNumber(orderID);
        if (order == null){
            throw new IllegalArgumentException("order not found");
        }
        if(customer == null){
            throw new IllegalArgumentException("customer not found");
        }
        order.setCustomer(customer);
        return specificOrderRepository.save(order);
    }
    
    
}

