package ca.mcgill.ecse321.videogamessystem.dto;

import java.sql.Date;

public class OrderRequestDto {

    private Date orderDate;
    private int cardNumber;
    private int customerId; // Assuming we use customer ID to associate an order with a customer

    // Constructors
    public OrderRequestDto() {
    }

    public OrderRequestDto(Date orderDate, int cardNumber, int customerId) {
        this.orderDate = orderDate;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
    }

    // Getters and Setters
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "orderDate=" + orderDate +
                ", cardNumber=" + cardNumber +
                ", customerId=" + customerId +
                '}';
    }
}
