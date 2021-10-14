package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {
	Firm findById(long id);
	Firm findByUsername(String username);
	Firm findByName(String name);
	
	@Transactional
	void deleteById(int id);
}
