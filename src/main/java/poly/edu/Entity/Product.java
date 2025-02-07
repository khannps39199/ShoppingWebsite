package poly.edu.Entity;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Transient;

import java.time.Instant;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
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
    private Category category;

    @Column(name = "Created_At", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Transient
    private MultipartFile imageFile;

    // Hàm tự động gán giá trị cho createdAt trước khi lưu vào database
    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
    }
}
