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
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SpecificOrderController {

    @Autowired
    private SpecificOrderService specificOrderService;

    @Autowired
    private CustomerService customerService;

    /**
     * Creates a new specific order based on the provided request DTO.
     *
     * @param orderRequestDto Data transfer object containing order details such as date, card number, and customer ID.
     * @return ResponseEntity containing the created order data in a response DTO format.
     */
    @PostMapping("/orders")
    public ResponseEntity<SpecificOrderResponseDto> createSpecificOrder(@Valid @RequestBody SpecificOrderRequestDto orderRequestDto) {
        SpecificOrder order = specificOrderService.createSpecificOrder(
                orderRequestDto.getOrderDate(),
                orderRequestDto.getCardNumber(),
                (long) orderRequestDto.getCustomerId()
        );
        return ResponseEntity.ok(convertToDto(order));
    }

    /**
     * Retrieves a specific order by its unique order number.
     *
     * @param orderNumber The unique number of the order to retrieve.
     * @return ResponseEntity containing the retrieved order data in a response DTO format.
     */
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<SpecificOrderResponseDto> getOrderById(@PathVariable int orderNumber) {
        SpecificOrder order = specificOrderService.getOrderById(orderNumber);
        return ResponseEntity.ok(convertToDto(order));
    }

    /**
     * Retrieves all specific orders available in the system.
     *
     * @return ResponseEntity containing a list of all orders in response DTO format.
     */
    @GetMapping("/orders")
    public ResponseEntity<List<SpecificOrderResponseDto>> getAllOrders() {
        List<SpecificOrderResponseDto> orders = specificOrderService.getAllOrders()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    /**
     * Updates the card number associated with a specific order.
     *
     * @param orderNumber   The unique number of the order to update.
     * @param newCardNumber The new card number to associate with the order.
     * @return ResponseEntity containing the updated order data in a response DTO format.
     */
    @PutMapping("/orders/{orderNumber}/cardNumber")
    public ResponseEntity<SpecificOrderResponseDto> updateCardNumber(
            @PathVariable int orderNumber,
            @RequestParam int newCardNumber) {
        SpecificOrder order = specificOrderService.updateCardNumber(orderNumber, newCardNumber);
        return ResponseEntity.ok(convertToDto(order));
    }

    /**
     * Deletes a specific order by its unique order number.
     *
     * @param orderNumber The unique number of the order to delete.
     * @return ResponseEntity with a status indicating the order has been deleted.
     */
    @DeleteMapping("/orders/{orderNumber}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderNumber) {
        specificOrderService.deleteOrder(orderNumber);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all orders associated with a specific customer by customer ID.
     *
     * @param customerId The ID of the customer whose orders are to be retrieved.
     * @return ResponseEntity containing a list of the customer's orders in response DTO format.
     */
    @GetMapping("/orders/customer/{customerId}")
    public ResponseEntity<List<SpecificOrderResponseDto>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<SpecificOrder> orders = specificOrderService.getOrdersByCustomer(customerId);
        List<SpecificOrderResponseDto> responseDtos = orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    /**
     * Assigns a specific order to a customer.
     *
     * @param orderNumber The unique number of the order to assign.
     * @param customerId  The ID of the customer to assign the order to.
     * @return ResponseEntity containing the assigned order data in a response DTO format.
     */
    @PutMapping("/orders/{orderNumber}/assignCustomer/{customerId}")
    public ResponseEntity<SpecificOrderResponseDto> assignOrderToCustomer(
            @PathVariable int orderNumber,
            @PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        SpecificOrder order = specificOrderService.placeNewOrder(orderNumber, customer);
        return ResponseEntity.ok(convertToDto(order));
    }

    /**
     * Helper method to convert a SpecificOrder model object to its corresponding DTO.
     *
     * @param order The SpecificOrder object to be converted.
     * @return SpecificOrderResponseDto containing the data from the order object.
     */
    private SpecificOrderResponseDto convertToDto(SpecificOrder order) {
        return new SpecificOrderResponseDto(
                order.getNumber(),
                order.getOrderDate(),
                order.getCardNumber(),
                order.getCustomer().getId()
        );
    }
}
