package poly.edu.DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String username;
    private String passwordHash;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String role = "Customer";
    private Boolean isActivated = false;
    private Timestamp createdAt;

}
