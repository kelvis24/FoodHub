package foodhub;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findById(int id);
	
	List<Customer> findByEmail(String email);
	
	@Transactional
	void deleteById(int id);
}
