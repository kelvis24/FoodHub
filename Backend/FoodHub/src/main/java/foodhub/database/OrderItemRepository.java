package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	List<OrderItem> findAll();
	
	OrderItem findById(long id);
	List<OrderItem> findByOrderId(long orderId);
	List<OrderItem> findByItemId(long itemId);
	
	void deleteById(int id);
	
}