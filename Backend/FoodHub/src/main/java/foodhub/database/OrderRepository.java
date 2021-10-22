package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findAll();
	
	Order findById(long id);
	List<Order> findByCustomerId(long customerId);
	List<Order> findByFirmId(long firmId);

	@Modifying
	@Transactional
	@Query("update Order x set x.status = ?2 where x.id = ?1")
	void setById(long id, String status);
	
	void deleteById(int id);
	
}