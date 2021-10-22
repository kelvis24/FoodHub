package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	List<Admin> findAll();

	Admin findById(long id);
	Admin findByUsername(String username);

	@Modifying
	@Transactional
	@Query("update Admin a set a.username = ?2, a.password = ?3, a.name = ?4, a.type = ?5 where a.id = ?1")
	void setAdminById(long id, String username, String password, String name, int type);
	
	void deleteById(long id);
	
}
