package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<Item, Long>{
	
	List<Item> findAll();
	
	Item findById(long id);
	List<Item> findByFirmId(long firmId);
	List<Item> findByCategoryId(long categoryId);
	
	void deleteById(long id);
}
