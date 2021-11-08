package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Specifies the operations that will be carried out by on the table of categories
 * 
 * @author 1_CW_2
 */
public interface CategoryRepository extends JpaRepository<Category, Long>  {

	/**
	 * Returns a list of all the categories within the table of categories
	 * @return A list of all categories
	 */
	List<Category> findAll();
	
	/**
	 * Returns the category with the given id
	 * @param id The id of the category that is returned
	 * @return The category with the given id
	 */
	Category findById(long id);
	
	/**
	 * Returns a list of all categories belonging to the firm with the given id
	 * @param firmId The id of the firm to which the returned categories belong
	 * @return A list of all the categories of the firm with the given id
	 */
	List<Category> findByFirmId(long firmId);

	/**
	 * Totally updates the information of the category with the given id
	 * @param id The id of the category which is to be updated
	 * @param title The new title of the id
	 * @param description The new description of the id
	 */
	@Modifying
	@Transactional
	@Query("update Category x set x.title = ?2, x.description = ?3 where x.id = ?1")
	void setById(long id, String title, String description);
	
	/**
	 * Deleted the category with the given id
	 * @param id The id of the category which is to be given
	 */
	void deleteById(long id);

}
