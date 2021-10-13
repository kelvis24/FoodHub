package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long>{
	Item findById(long id);
	Item findByTitle(String title);
	List<Item> findByFirmId(long firmId);
	
	@Transactional
	void deleteById(int id);
}
