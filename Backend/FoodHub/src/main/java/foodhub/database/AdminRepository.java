package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	List<Admin> findAll();

	Admin findById(long id);
	Admin findByUsername(String username);
	
	void deleteById(int id);
	
}
