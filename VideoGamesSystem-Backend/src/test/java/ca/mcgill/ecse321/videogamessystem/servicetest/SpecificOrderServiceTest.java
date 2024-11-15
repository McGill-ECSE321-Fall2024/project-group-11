package ca.mcgill.ecse321.videogamessystem.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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



    @Test
    public void testGetOrdersByOrderDate_WithExistingOrders() {
        // Arrange
        Date orderDate = Date.valueOf("2023-10-15");
        SpecificOrder order1 = new SpecificOrder(1, orderDate, 123456);
        SpecificOrder order2 = new SpecificOrder(2, orderDate, 654321);
        
        when(specificOrderRepository.findOrderByOrderDate(orderDate)).thenReturn(List.of(order1, order2));

        // Act
        List<SpecificOrder> orders = orderService.getOrdersByOrderDate(orderDate);

        // Assert
        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
        verify(specificOrderRepository, times(1)).findOrderByOrderDate(orderDate);
    }

    @Test
    public void testUpdateCardNumber_Success() {
        // Arrange
        int orderNumber = 1;
        int newCardNumber = 12345678;
        SpecificOrder order = new SpecificOrder();
        order.setCardNumber(87654321); // Set initial card number for comparison

        when(specificOrderRepository.findOrderByNumber(orderNumber)).thenReturn(order);
        when(specificOrderRepository.save(order)).thenReturn(order);

        // Act
        SpecificOrder updatedOrder = orderService.updateCardNumber(orderNumber, newCardNumber);

        // Assert
        assertNotNull(updatedOrder);
        assertEquals(newCardNumber, updatedOrder.getCardNumber());
        verify(specificOrderRepository, times(1)).save(order);
    }
    @Test
    public void testUpdateCardNumber_OrderNotFound() {
        // Arrange
        int orderNumber = 999;
        int newCardNumber = 12345678;

        when(specificOrderRepository.findOrderByNumber(orderNumber)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateCardNumber(orderNumber, newCardNumber);
        });
        assertEquals("Order not found.", exception.getMessage());
    }

    @Test
    public void testUpdateCardNumber_InvalidCardNumber() {
        // Arrange
        int orderNumber = 1;
        int invalidCardNumber = -12345;
        SpecificOrder order = new SpecificOrder();

        when(specificOrderRepository.findOrderByNumber(orderNumber)).thenReturn(order);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateCardNumber(orderNumber, invalidCardNumber);
        });
        assertEquals("Card number must be valid.", exception.getMessage());
    }


    @Test
    public void testGetAllOrders_WithOrders() {
        // Arrange
        SpecificOrder order1 = new SpecificOrder();
        SpecificOrder order2 = new SpecificOrder();
        List<SpecificOrder> orders = List.of(order1, order2);

        when(specificOrderRepository.findAll()).thenReturn(orders);

        // Act
        List<SpecificOrder> retrievedOrders = orderService.getAllOrders();

        // Assert
        assertNotNull(retrievedOrders, "The result should not be null.");
        assertEquals(2, retrievedOrders.size(), "The order list size should match the number of orders.");
        assertTrue(retrievedOrders.containsAll(orders), "The result should contain all stored orders.");
    }

    @Test
    public void testPlaceNewOrder_Success() {
        // Arrange
        int orderID = 1;
        SpecificOrder order = new SpecificOrder();
        Customer customer = new Customer();
        
        when(specificOrderRepository.findOrderByNumber(orderID)).thenReturn(order);
        when(specificOrderRepository.save(any(SpecificOrder.class))).thenReturn(order);

        // Act
        SpecificOrder updatedOrder = orderService.placeNewOrder(orderID, customer);

        // Assert
        assertNotNull(updatedOrder, "The updated order should not be null.");
        assertEquals(customer, updatedOrder.getCustomer(), "The customer should be assigned to the order.");
        verify(specificOrderRepository, times(1)).save(order);
    }

    @Test
    public void testPlaceNewOrder_OrderNotFound() {
        // Arrange
        int orderID = 1;
        Customer customer = new Customer();
        
        when(specificOrderRepository.findOrderByNumber(orderID)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeNewOrder(orderID, customer);
        });
        assertEquals("order not found", exception.getMessage());
    }

    @Test
    public void testPlaceNewOrder_CustomerIsNull() {
        // Arrange
        int orderID = 1;
        SpecificOrder order = new SpecificOrder();
        
        when(specificOrderRepository.findOrderByNumber(orderID)).thenReturn(order);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeNewOrder(orderID, null);
        });
        assertEquals("customer not found", exception.getMessage());
    }
}
