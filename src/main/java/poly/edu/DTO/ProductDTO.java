package poly.edu.DTO;

import java.sql.Timestamp;

public class ProductDTO {

    private Long id;  // Đổi kiểu từ Integer sang Long
    private String name;
    private String description;
    private double price;
    private double discount;
    private int stock;
    private String image;
    private Timestamp createdAt;
    private Long idCategory;  // Đổi kiểu từ Integer sang Long

    public ProductDTO(Long id, String name, String description, double price, double discount, int stock,
                      String image, Timestamp createdAt, Long idCategory) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

}