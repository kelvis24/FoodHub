package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findById(long id);
	Admin findByUsername(String username);
	
	@Transactional
	void deleteById(int id);
	
}
