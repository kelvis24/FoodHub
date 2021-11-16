package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Specifies the operations that will occur upon the table of firms
 * @author 1_CW_2
 */
public interface FirmRepository extends JpaRepository<Firm, Long> {
	
	/**
	 * Returns a list of all firms
	 * @return A list of all firms
	 */
	List<Firm> findAll();
	
	/**
	 * Returns the firm with the given id
	 * @param id The id of the firm that is returned
	 * @return The firm with the given id
	 */
	Firm findById(long id);
	
	/**
	 * Returns the firm with the given username
	 * @param username The username of the firm that is returned
	 * @return The firm with the given username
	 */
	Firm findByUsername(String username);

	/**
	 * Totally updates a firm with the given id with new, enumerated information
	 * @param id The id of the firm that is to be updated
	 * @param username The new username of the firm
	 * @param password The new password of the firm
	 * @param name The new name of the firm
	 * @param location The new location of the firm
	 * @param cuisine The new cuisine of the firm
	 * @param open_time The new opening time of the firm, as expressed as an int
	 * @param close_time The new closing time of the firm, as expressed as an int
	 * @param employee_count The new recognized number of employees at the firm
	 */
	@Modifying
	@Transactional
	@Query("update Firm x set x.username = ?2, x.password = ?3, x.name = ?4, x.location = ?5,"
		+ " x.cuisine = ?6, x.open_time = ?7, x.close_time = ?8, x.employee_count = ?9, x.imageSource = ?10 where x.id = ?1")
	void setById(long id, String username, String password, String name, String location,
					 String cuisine, int open_time, int close_time, int employee_count, String imageSource);
	
	/**
	 * Deletes the firm with the given id
	 * @param id The id of the given firm
	 */
	void deleteById(long id);
	
}
