package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {
	Category findById(long id);
	Category findByTitle(String title);
	
	@Transactional
	void deleteById(int id);

}
