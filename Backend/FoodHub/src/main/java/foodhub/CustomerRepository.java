package foodhub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findById(int id);
	
	@Transactional
	void deleteById(int id);
	
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Customer findByEmail(String email);
}
