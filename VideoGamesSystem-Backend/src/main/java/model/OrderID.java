package main.java.model;
import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class OrderId implements Serializable {

    private Long customerId;
    private Long orderId;

    // Constructors
    public OrderId() {
    }

    public OrderId(Long customerId, Long orderId) {
        this.customerId = customerId;
        this.orderId = orderId;
    }

    // Getters and setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // equals() and hashCode() methods (required for composite keys)
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderId orderId1 = (OrderId) o;
        return customerId.equals(orderId1.customerId) && orderId.equals(orderId1.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, orderId);
    }
}
