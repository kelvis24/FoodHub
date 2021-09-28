package foodhub;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findById(int id);
	
	List<Admin> findByEmail(String email);
	
	@Transactional
	void deleteById(int id);
	
}
