package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository  extends JpaRepository<Item, Long>{
	
	List<Item> findAll();
	
	Item findById(long id);
	List<Item> findByFirmId(long firmId);
	List<Item> findByCategoryId(long categoryId);

	@Modifying
	@Transactional
	@Query("update Category x set x.title = ?2, x.description = ?3, x.price = ?4, where x.id = ?1")
	void setById(long id, String title, String description, double price);
	
	void deleteById(long id);
	
}
