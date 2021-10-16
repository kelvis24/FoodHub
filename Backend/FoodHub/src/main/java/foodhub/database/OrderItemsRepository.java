package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	OrderItems findById(long id);
	List<OrderItems> findByOrderId(long orderId);
	
	@Transactional
	void deleteById(int id);
}