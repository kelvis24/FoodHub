package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

	List<Category> findAll();
	
	Category findById(long id);
	List<Category> findByFirmId(long firmId);

	@Modifying
	@Transactional
	@Query("update Category x set x.title = ?2, x.description = ?3 where x.id = ?1")
	void setById(long id, String title, String description);
	
	void deleteById(long id);

}
