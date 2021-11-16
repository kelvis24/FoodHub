package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OTMessageRepository extends JpaRepository<OTMessage, Long> {
	
	List<OTMessage> findAll();

	List<OTMessage> findByOrderId(long orderId);
	
	void deleteById(int id);
	
}