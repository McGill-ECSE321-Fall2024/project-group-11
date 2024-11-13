package ca.mcgill.ecse321.videogamessystem.service_tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.repository.SpecificOrderRepository;
import ca.mcgill.ecse321.videogamessystem.repository.CustomerRepository;
import ca.mcgill.ecse321.videogamessystem.service.SpecificOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class SpecificOrderServiceTest {

    @Mock
    private SpecificOrderRepository specificOrderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private SpecificOrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSpecificOrder_Success() {
        // Arrange
        Date orderDate = new Date(System.currentTimeMillis());
        int cardNumber = 12345678;
        Long customerId = 1L;
        Customer customer = new Customer();
        
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        SpecificOrder order = new SpecificOrder(0, orderDate, cardNumber);
        order.setCustomer(customer);
        when(specificOrderRepository.save(any(SpecificOrder.class))).thenReturn(order);

        // Act
        SpecificOrder createdOrder = orderService.createSpecificOrder(orderDate, cardNumber, customerId);

        // Assert
        assertNotNull(createdOrder);
        assertEquals(orderDate, createdOrder.getOrderDate());
        assertEquals(cardNumber, createdOrder.getCardNumber());
        assertEquals(customer, createdOrder.getCustomer());
    }

    @Test
    public void testGetOrderById_OrderExists() {
        // Arrange
        int orderId = 1;
        SpecificOrder order = new SpecificOrder();
        when(specificOrderRepository.findOrderByNumber(orderId)).thenReturn(order);

        // Act
        SpecificOrder foundOrder = orderService.getOrderById(orderId);

        // Assert
        assertNotNull(foundOrder);
        verify(specificOrderRepository, times(1)).findOrderByNumber(orderId);
    }

    @Test
    public void testGetOrderById_OrderNotFound() {
        // Arrange
        int orderId = 1;
        when(specificOrderRepository.findOrderByNumber(orderId)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrderById(orderId);
        });
        assertEquals("Order not found.", exception.getMessage());
    }

    @Test
    public void testUpdateOrderDate_Success() {
        // Arrange
        int orderId = 1;
        Date newOrderDate = new Date(System.currentTimeMillis());
        SpecificOrder order = new SpecificOrder();
        when(specificOrderRepository.findOrderByNumber(orderId)).thenReturn(order);
        when(specificOrderRepository.save(order)).thenReturn(order);

        // Act
        SpecificOrder updatedOrder = orderService.updateOrderDate(orderId, newOrderDate);

        // Assert
        assertNotNull(updatedOrder);
        assertEquals(newOrderDate, updatedOrder.getOrderDate());
    }

    @Test
    public void testDeleteOrder_Success() {
        // Arrange
        int orderId = 1;
        SpecificOrder order = new SpecificOrder();
        when(specificOrderRepository.findOrderByNumber(orderId)).thenReturn(order);

        // Act
        SpecificOrder deletedOrder = orderService.deleteOrder(orderId);

        // Assert
        assertNotNull(deletedOrder);
        verify(specificOrderRepository, times(1)).delete(order);
    }

    @Test
    public void testGetOrdersByCustomer() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        List<SpecificOrder> orders = new ArrayList<>();
        orders.add(new SpecificOrder());

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(specificOrderRepository.findOrderByCustomer(customer)).thenReturn(orders);

        // Act
        List<SpecificOrder> customerOrders = orderService.getOrdersByCustomer(customerId);

        // Assert
        assertNotNull(customerOrders);
        assertEquals(1, customerOrders.size());
    }
}
