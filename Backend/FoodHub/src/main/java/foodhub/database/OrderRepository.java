package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findAll();
	
	Order findById(long id);
	List<Order> findByCustomerId(long customerId);
	List<Order> findByFirmId(long firmId);
	
	void deleteById(int id);
}