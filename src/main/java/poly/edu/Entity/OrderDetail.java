package poly.edu.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "OrderDetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    private Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false, referencedColumnName = "OrderID") // FK to Orders
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false, referencedColumnName = "ProductID") // FK to Products
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Price", nullable = false)
    private double price; // Price at the time of purchase

    @Column(name = "Discount", nullable = false, columnDefinition = "decimal(5, 2) default 0")
    private double discount;

	
    
}
