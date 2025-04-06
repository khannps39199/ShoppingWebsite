package poly.edu.DTO;

import java.sql.Timestamp;

import poly.edu.Repository.CategoryRepository;

public class CategoryDTO {
    private Integer categoryId;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Long productCount;
}
