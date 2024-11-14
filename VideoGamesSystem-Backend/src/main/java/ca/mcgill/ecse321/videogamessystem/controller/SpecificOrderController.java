package ca.mcgill.ecse321.videogamessystem.controller;

import ca.mcgill.ecse321.videogamessystem.dto.SpecificOrderDto.SpecificOrderRequestDto;
import ca.mcgill.ecse321.videogamessystem.dto.SpecificOrderDto.SpecificOrderResponseDto;
import ca.mcgill.ecse321.videogamessystem.model.Customer;
import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;
import ca.mcgill.ecse321.videogamessystem.service.CustomerService;
import ca.mcgill.ecse321.videogamessystem.service.SpecificOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class SpecificOrderController {

    @Autowired
    private SpecificOrderService specificOrderService;

    @Autowired
    private CustomerService customerService;

    // Create a new order
    @PostMapping
    public ResponseEntity<SpecificOrderResponseDto> createSpecificOrder(@RequestBody SpecificOrderRequestDto orderRequestDto) {
        SpecificOrder order = specificOrderService.createSpecificOrder(
                orderRequestDto.getOrderDate(),
                orderRequestDto.getCardNumber(),
                (long) orderRequestDto.getCustomerId()
        );
        return ResponseEntity.ok(convertToDto(order));
    }

    // Get order by order number
    @GetMapping("/{orderNumber}")
    public ResponseEntity<SpecificOrderResponseDto> getOrderById(@PathVariable int orderNumber) {
        SpecificOrder order = specificOrderService.getOrderById(orderNumber);
        return ResponseEntity.ok(convertToDto(order));
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<SpecificOrderResponseDto>> getAllOrders() {
        List<SpecificOrderResponseDto> orders = specificOrderService.getAllOrders()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    // Update order date
    @PutMapping("/{orderNumber}/date")
    public ResponseEntity<SpecificOrderResponseDto> updateOrderDate(
            @PathVariable int orderNumber,
            @RequestParam Date newOrderDate) {
        SpecificOrder order = specificOrderService.updateOrderDate(orderNumber, newOrderDate);
        return ResponseEntity.ok(convertToDto(order));
    }

    // Update card number
    @PutMapping("/{orderNumber}/cardNumber")
    public ResponseEntity<SpecificOrderResponseDto> updateCardNumber(
            @PathVariable int orderNumber,
            @RequestParam int newCardNumber) {
        SpecificOrder order = specificOrderService.updateCardNumber(orderNumber, newCardNumber);
        return ResponseEntity.ok(convertToDto(order));
    }

    // Delete an order
    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderNumber) {
        specificOrderService.deleteOrder(orderNumber);
        return ResponseEntity.noContent().build();
    }

    // Get orders by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SpecificOrderResponseDto>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<SpecificOrder> orders = specificOrderService.getOrdersByCustomer(customerId);
        List<SpecificOrderResponseDto> responseDtos = orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    // Assign order to customer
    @PutMapping("/{orderNumber}/assignCustomer/{customerId}")
    public ResponseEntity<SpecificOrderResponseDto> assignOrderToCustomer(
            @PathVariable int orderNumber,
            @PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        SpecificOrder order = specificOrderService.placeNewOrder(orderNumber, customer);
        return ResponseEntity.ok(convertToDto(order));
    }

    // Helper method to convert SpecificOrder to SpecificOrderResponseDto
    private SpecificOrderResponseDto convertToDto(SpecificOrder order) {
        return new SpecificOrderResponseDto(
                order.getNumber(),
                order.getOrderDate(),
                order.getCardNumber(),
                order.getCustomer() != null ? order.getCustomer().getId().intValue() : null
        );
    }
}

