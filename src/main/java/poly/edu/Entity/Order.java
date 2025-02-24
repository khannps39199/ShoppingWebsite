package poly.edu.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false, referencedColumnName = "UserID") // FK to Users
    private User user;

    @Column(name = "Order_Date", nullable = false, columnDefinition = "datetime default GETDATE()")
    private Timestamp orderDate = Timestamp.from(Instant.now());

    @Column(name = "Status", length = 50, nullable = false)
    private String status = "Pending"; // Mặc định là Pending

    @Column(name = "Total_Amount", nullable = false)
    private double totalAmount;

    @Column(name = "Shipping_Address", length = 255)
    private String shippingAddress;

    @Column(name = "Payment_Method", length = 50, nullable = false)
    private String paymentMethod = "WHEN_RECEIVE"; // Mặc định là WHEN_RECEIVE

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    // Constructor tùy chỉnh
    public Order(User user, Timestamp orderDate, double totalAmount, String status, String shippingAddress, String paymentMethod) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }
}
