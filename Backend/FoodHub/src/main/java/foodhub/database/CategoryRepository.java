package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {
	Category findById(long id);
	Category findByTitle(String title);
	List<Category> findByFirm(long firmId);
	
	@Transactional
	void deleteById(int id);

}
