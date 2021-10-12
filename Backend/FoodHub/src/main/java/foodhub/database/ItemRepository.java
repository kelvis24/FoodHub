package foodhub.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long>{
	Item findById(long id);
	Item findByTitle(String title);
	
	@Transactional
	void deleteById(int id);
}
