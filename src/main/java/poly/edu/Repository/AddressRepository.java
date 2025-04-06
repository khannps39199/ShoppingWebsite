package poly.edu.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findById(long id);
}
