package poly.edu.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Page<User> findAll(Pageable pageable);
	 @Query("SELECT u FROM User u WHERE u.username = :name")
	   Optional<User> findByUsername(@Param("name") String name);
	 @Query("SELECT u FROM User u WHERE u.email = :keyword")
	 Optional<User> searchByEmail(@Param("keyword") String keyword);
}
