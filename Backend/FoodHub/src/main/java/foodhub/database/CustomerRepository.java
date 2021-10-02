package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findById(int id);
	Customer findByUsername(String userName);
	
	@Transactional
	void deleteById(int id);
}
