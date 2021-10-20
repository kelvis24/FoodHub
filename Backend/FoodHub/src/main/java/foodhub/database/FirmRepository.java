package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm, Long> {
	
	List<Firm> findAll();
	
	Firm findById(long id);
	Firm findByUsername(String username);
	
	void deleteById(long id);
}
