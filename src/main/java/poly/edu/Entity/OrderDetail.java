package poly.edu.Entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Detail_ID")
    private Integer orderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false, referencedColumnName = "OrderID")
    private Order order;

    @Column(name = "ProductID", nullable = false)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "ProductID", insertable = false, updatable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Discount", nullable = false, columnDefinition = "decimal(5, 2) default 0")
    private double discount;
}
