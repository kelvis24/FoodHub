package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AMessageRepository extends JpaRepository<AMessage, Long>{
	List<AMessage> findAll();
	void deleteById(int id);
}
