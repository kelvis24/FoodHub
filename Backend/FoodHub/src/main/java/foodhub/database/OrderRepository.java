package foodhub.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository {
	Order findById(long id);
	Order findByCustomerId(long customerId);
	
	@Transactional
	void deleteById(int id);
}
