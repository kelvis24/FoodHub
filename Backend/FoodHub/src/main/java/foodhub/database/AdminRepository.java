package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findById(long id);
	Admin findByUserName(String userName);
	
	List<Admin> findByType(int type);
	
	@Transactional
	void deleteById(int id);
	
}
