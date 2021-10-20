package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	
	List<OrderItems> findall();
	
	OrderItems findById(long id);
	List<OrderItems> findByOrderId(long orderId);
	List<OrderItems> findByItemId(long itemId);
	
	void deleteById(int id);
}