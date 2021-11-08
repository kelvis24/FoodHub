package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * A model of the table of admins in the mysql database.
 * 
 * @author 1_CW_2
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	/**
	 * Returns a list of all admins
	 * @return All admins in the table
	 */
	List<Admin> findAll();

	/**
	 * Returns the admin with the corresponding id
	 * @param id the id of the admin returned
	 * @return the admin of the id received
	 */
	Admin findById(long id);
	
	/**
	 * Returns the admin with the corresponding username
	 * @param username of the admin returned
	 * @return the admin of the username received
	 */
	Admin findByUsername(String username);

	/**
	 * Totally updates the admin of the given id with new information
	 * @param id the id of the admin that will be updated
	 * @param username the new username that admin will be given 
	 * @param password the new password that admin will be given
	 * @param name the new name that admin will be given
	 * @param type the new type whereby that admin will be recognized
	 */
	@Modifying
	@Transactional
	@Query("update Admin x set x.username = ?2, x.password = ?3, x.name = ?4, x.type = ?5 where x.id = ?1")
	void setById(long id, String username, String password, String name, int type);
	
	/**
	 * Deletes the admin with the given id
	 * @param id the id of the admin which is to be deleted
	 */
	void deleteById(long id);
	
}
