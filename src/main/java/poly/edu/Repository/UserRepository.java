package poly.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
