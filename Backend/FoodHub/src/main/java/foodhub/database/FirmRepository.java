package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FirmRepository extends JpaRepository<Firm, Long> {
	
	List<Firm> findAll();
	
	Firm findById(long id);
	Firm findByUsername(String username);

	@Modifying
	@Transactional
	@Query("update Firm x set x.username = ?2, x.password = ?3, x.name = ?4, x.location = ?5,"
		+ " x.cuisine = ?6, x.open_time = ?7, x.close_time = ?8, x.employee_count = ?9 where x.id = ?1")
	void setById(long id, String username, String password, String name, String location,
					 String cuisine, int open_time, int close_time, int employee_count);
	
	void deleteById(long id);
	
}
