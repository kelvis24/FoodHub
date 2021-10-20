package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

	List<Category> findAll();
	
	Category findById(long id);
	List<Category> findByFirmId(long firmId);
	
	void deleteById(long id);

}
