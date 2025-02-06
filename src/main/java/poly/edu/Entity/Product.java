package poly.edu.Entity;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor  // Lombok tự động tạo constructor không tham số
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long id;

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
    private Category category;  // Mối quan hệ khóa ngoại với Category

    @Column(name = "Created_At", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Transient
    private String idCategory;

    // Phương thức này trả về mã danh mục dưới dạng String
    public String getIdCategory() {
        return (category != null) ? String.valueOf(category.getId()) : "0";
    }

    // Constructor đầy đủ
    public Product(Long id, String name, String description, double price, double discount, int stock, String image,
                   Timestamp createdAt, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.image = image;
        this.createdAt = createdAt;
        this.category = category;
    }

    // Constructor với idCategory là String (thường dùng khi nhập từ form)
    public Product(Long id, String name, String description, double price, double discount, int stock, String image,
                   Timestamp createdAt, String idCategory) {
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
}