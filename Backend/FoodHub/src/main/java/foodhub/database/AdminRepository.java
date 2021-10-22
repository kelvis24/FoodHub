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
	@Query("update Admin x set x.username = ?2, x.password = ?3, x.name = ?4, x.type = ?5 where x.id = ?1")
	void setById(long id, String username, String password, String name, int type);
	
	void deleteById(long id);
	
}
