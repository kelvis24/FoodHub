package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findAll();
	
	Customer findById(long id);
	Customer findByUsername(String username);
	
	void deleteById(long id);
}
