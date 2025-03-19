package poly.edu.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
	 	
		public Discount(Product ByID, Float discountValue2) {
			this.product=ByID;
			this.discountValue=discountValue2;
		}
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "DiscountID")
	    private Long discountId;
	 	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "ProductID", nullable = false)
	    private Product product; 
	 	 @Column(name = "Discount_Value")
	     private Float discountValue;
}
