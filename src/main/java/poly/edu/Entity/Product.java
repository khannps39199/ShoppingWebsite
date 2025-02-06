package poly.edu.Entity;

import java.util.Date;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ProductID")
	    private Integer id;

	    @Column(name = "Name", nullable = false)
	    private String name;

	    @Column(name = "Description")
	    private String description;

	    @Column(name = "Price", nullable = false)
	    private double price;

	    @Column(name = "Discount", nullable = false)
	    private double discount;

	    @Column(name = "Stock", nullable = false)
	    private int stock;

	    @Column(name = "Image")
	    private String image;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "CategoryID", nullable = false)
	    private Category category;  // This is the foreign key relationship

	    @Column(name = "Created_At", updatable = false, nullable = false)
	    private Timestamp createdAt;
	    
	    @Transient
	    private String idCategory;

	    public String getIdCategory() {
	        return (category != null) ? String.valueOf(category.getId())  : "0";
	    }
	public int getProductId() {
		return id;
	}
	
	
	public Product(Integer id, String name, String description, double price, double discount, int stock, String image,
			 Timestamp createdAt, String idCategory) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.image = image;
		this.createdAt = createdAt;
		this.idCategory = idCategory;
	}
	public void setProductId(int productId) {
		this.id = productId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Product() {
		super();
	}

	public Product(int productId, String name, String description, double price, double discount, int stock,
			String image, Category category, Timestamp createdAt) {
		super();
		this.id = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.image = image;
		this.category = category;
		this.createdAt = createdAt;
	}
    
}
