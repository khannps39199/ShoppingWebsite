package poly.edu.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_orders")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_order_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Order order;

    @Column(name = "Status", length = 50, nullable = false, columnDefinition = "nvarchar(50) default 'PENDING'")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Updated_At")
    private Date updatedAt = new Date();
}
