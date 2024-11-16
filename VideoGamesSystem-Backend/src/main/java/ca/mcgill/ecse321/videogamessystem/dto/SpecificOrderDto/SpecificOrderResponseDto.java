package ca.mcgill.ecse321.videogamessystem.dto.SpecificOrderDto;

import java.sql.Date;

import ca.mcgill.ecse321.videogamessystem.model.SpecificOrder;

public class SpecificOrderResponseDto {

    private int orderNumber;
    private Date orderDate;
    private int cardNumber;
    private Long customerId;

    // Default constructor
    public SpecificOrderResponseDto() {
    }

    // Constructor with all attributes
    public SpecificOrderResponseDto(int orderNumber, Date orderDate, int cardNumber, Long customerId) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
    }

    // Constructor that initializes from SpecificOrder
    public SpecificOrderResponseDto(SpecificOrder order) {
        this.orderNumber = order.getNumber();
        this.orderDate = order.getOrderDate();
        this.cardNumber = order.getCardNumber();
        this.customerId = (order.getCustomer() != null) ? order.getCustomer().getId() : null;
    }

    // Getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    // Setters
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", cardNumber=" + cardNumber +
                ", customerId=" + customerId +
                '}';
    }
}
