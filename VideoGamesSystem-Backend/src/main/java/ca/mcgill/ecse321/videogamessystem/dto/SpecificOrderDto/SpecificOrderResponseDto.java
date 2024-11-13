package ca.mcgill.ecse321.videogamessystem.dto.SpecificOrderDto;

import java.sql.Date;

public class SpecificOrderResponseDto {

    private int orderNumber;
    private Date orderDate;
    private int cardNumber;
    private int customerId; // Assuming we use customer ID to associate an order with a customer

    // Constructors
    public SpecificOrderResponseDto() {
    }

    public SpecificOrderResponseDto(int orderNumber, Date orderDate, int cardNumber, int customerId) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    // Setters (if needed, typically not used for response DTOs)
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCustomerId(int customerId) {
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
