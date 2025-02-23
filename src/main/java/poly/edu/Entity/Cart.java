package poly.edu.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class Cart {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CartID")
	    private Long cartId;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "UserID", nullable = false)
	    private User user; // Reference to User entity

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "ProductID", nullable = false, unique = false)
	    private Product product; // Reference to Product entity

	    @Column(name = "Quantity", nullable = false, columnDefinition = "int default 1")
	    private Integer quantity = 1;

	    @Column(name = "Added_At", nullable = false)
	    private Timestamp addedAt;

	    @PrePersist
	    protected void onCreate() {
	        if (addedAt == null) {
	            addedAt = Timestamp.from(Instant.now());
	        }
	    }

	    // Custom constructor
	    public Cart(User user, Product product, Integer quantity) {
	        this.user = user;
	        this.product = product;
	        this.quantity = quantity;
	        this.addedAt = Timestamp.from(Instant.now()); // Automatically set AddedAt to now
	    }

	    
}
