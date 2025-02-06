package poly.edu.Entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public User(Integer userId, String username, String passwordHash, String email, String fullName, String phone,
			String address, String role, Boolean isActivated, Timestamp createdAt) {
		super();
		this.userId = userId;
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.isActivated = isActivated;
		this.createdAt = createdAt;
	}
    
}
