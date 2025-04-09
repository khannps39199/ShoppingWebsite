package poly.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ServletComponentScan
@EntityScan(basePackages = "poly.edu.Entity")
public class Ps39199NguyenNhutKhaAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ps39199NguyenNhutKhaAssignmentApplication.class, args);
    }

}
