package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findById(int id);
	
	List<Customer> findByEmail(String email);
	
	Customer findByUserName(String username);
	
	@Transactional
	void deleteById(int id);
}
