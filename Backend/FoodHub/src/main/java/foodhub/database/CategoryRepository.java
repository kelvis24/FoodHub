package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findById(int id);
	Category findByTitle(String title);
	
	@Transactional
	void deleteById(int id);
}
