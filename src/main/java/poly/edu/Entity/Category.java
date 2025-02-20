package poly.edu.Entity;  // Ensure correct spelling of 'Entity' (it was 'Enity' earlier)

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity  // Marks the class as a JPA entity
@Data

@Table(name = "categories")  // Optional: Defines the table name
public class   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")  // Matches the column name in the database
    private Integer id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreatedAt", updatable = false, nullable = false)  // Correct mapping for CreatedAt
    private Timestamp createdAt;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    
    @Transient
    private int countProduct;

    public int getCountProduct() {
        return (products != null) ? products.size() : 0;
    }
	
	public Category(Integer id, String name, String description, Timestamp createdAt, int countProduct) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
		this.countProduct = countProduct;
	}

	public Category(Integer id, String name, String description, Timestamp createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
	}
	public Integer getCategoryID() {
	    return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Category() {
		super();
	}


	

    
}
