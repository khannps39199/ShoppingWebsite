package poly.edu.DTO;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import poly.edu.Entity.Category;

public class ProductDTO {
	
	    private Integer id;
	    private String name;
	    private String description;
	    private double price;
	    private double discount;
	    private int stock;
	    private String image;
	    private Timestamp createdAt;
	    private Integer idCategory;
	    
	    
		public ProductDTO(Integer id, String name, String description, double price, double discount, int stock,
				String image, Timestamp createdAt, Integer idCategory) {
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
		
		public Integer getId() {
			return id;
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
		public Timestamp getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
		public Integer getIdCategory() {
			return idCategory;
		}
		public void setIdCategory(Integer idCategory) {
			this.idCategory = idCategory;
		}
	    
}

