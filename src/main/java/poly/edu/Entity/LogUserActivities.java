package poly.edu.Entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "UserActivity")
public class LogUserActivities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActivityID")
    private Integer activityId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false, referencedColumnName = "UserID") // FK to Users
    private User user;

    @Column(name = "ActivityType", length = 50)
    private String activityType; // Examples: Register, ResetPassword, etc.

    @Column(name = "ActivityDate", nullable = false, columnDefinition = "datetime default GETDATE()")
    private Timestamp activityDate;

    @Column(name = "Token", length = 255)
    private String token; // Authentication token if required

    @Column(name = "IsCompleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean isCompleted;
}
