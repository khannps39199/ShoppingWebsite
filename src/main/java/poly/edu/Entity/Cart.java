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
	    private Integer cartId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "UserID", nullable = false)
	    private User user; // Reference to User entity

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "ProductID", nullable = false)
	    private Product product; // Reference to Product entity

	    @Column(name = "Quantity", nullable = false, columnDefinition = "int default 1")
	    private Integer quantity = 1;

	    @Column(name = "AddedAt", nullable = false, columnDefinition = "datetime default GETDATE()")
	    private Timestamp addedAt;

	    // Custom constructor
	    public Cart(User user, Product product, Integer quantity) {
	        this.user = user;
	        this.product = product;
	        this.quantity = quantity;
	        this.addedAt = Timestamp.from(Instant.now()); // Automatically set AddedAt to now
	    }

		public Integer getCartId() {
			return cartId;
		}

		public void setCartId(Integer cartId) {
			this.cartId = cartId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Timestamp getAddedAt() {
			return addedAt;
		}

		public void setAddedAt(Timestamp addedAt) {
			this.addedAt = addedAt;
		}
	    
}
