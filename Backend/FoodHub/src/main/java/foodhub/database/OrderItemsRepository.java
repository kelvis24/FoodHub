package foodhub.database;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderItemsRepository {
	OrderItems findById(long id);
	OrderItems findByOrderId(long orderId);
	
	@Transactional
	void deleteById(int id);
}
