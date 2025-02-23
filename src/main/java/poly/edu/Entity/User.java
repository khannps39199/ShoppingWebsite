package poly.edu.Entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "FullName", length = 100)
    private String fullName;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "Role", length = 20, columnDefinition = "nvarchar(20) default 'Customer'")
    private String role = "Customer";

    @Column(name = "IsActivated", nullable = false, columnDefinition = "bit default 0")
    private Boolean isActivated = false;

    @Column(name = "CreatedAt", nullable = false, columnDefinition = "datetime default GETDATE()")
    private Timestamp createdAt;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cart> carts;


	

	
    
}
