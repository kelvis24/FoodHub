package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Specifies the operations that will be enacted upon the table of items
 * @author 1_CW_2
 */
public interface ItemRepository  extends JpaRepository<Item, Long>{
	
	/**
	 * Returns a list of all items
	 * @return A list of all items
	 */
	List<Item> findAll();
	
	/**
	 * Returns the item of the specified id
	 * @param id The id of the item
	 * @return The item with the given id
	 */
	Item findById(long id);
	
	/**
	 * Returns a list of the items that belong to the firm with the given id
	 * @param firmId The id of the firm to which the returned items belong
	 * @return A list of the items that belong to the firm with the given id
	 */
	List<Item> findByFirmId(long firmId);
	
	/**
	 * Returns a list of the items that belong to the category with the given id
	 * @param categoryId The id of the category to which the returned items belong
	 * @return A list of the items that belong to the category with the given id
	 */
	List<Item> findByCategoryId(long categoryId);

	/**
	 * Totally updated an id with new, enumerated information
	 * @param id The id of the item that will be updated
	 * @param title The new title of the item
	 * @param description The new description of the item
	 * @param price The new price of the item
	 */
	@Modifying
	@Transactional
	@Query("update Item x set x.title = ?2, x.description = ?3, x.price = ?4 where x.id = ?1")
	void setById(long id, String title, String description, double price);
	
	/**
	 * Deleted the item with the given id
	 * @param id The id of the item that will be deleted
	 */
	void deleteById(long id);
	
}
