package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Order findById(long id);
	Order findByCustomerId(long customerId);
	
	@Transactional
	void deleteById(int id);
}