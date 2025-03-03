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
@Table(name = "Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	 	public Address(User byId, String address2) {
	 		this.user=byId;
	 		this.address=address2;
	 				
	 	}
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "AddressID")
	    private Long addressId;
	 	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "UserID", nullable = false)
	    private User user; // Reference to User entity
	 	 @Column(name = "Address", length = 255)
	     private String address;
}
