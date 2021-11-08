package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Specifies the operations that will occur upon the table of customers
 * @author 1_CW_2
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	/**
	 * Returns a list of all customers
	 * @return A list of all customers
	 */
	List<Customer> findAll();
	
	/**
	 * Returns the customer with the given id
	 * @param id The id of the customer returned
	 * @return The customer with the given id
	 */
	Customer findById(long id);
	
	/**
	 * Returns the customer with the given username
	 * @param username The username of the customer returned
	 * @return The customer with the given id
	 */
	Customer findByUsername(String username);

	/**
	 * Totally updates the customer with the given id with new information
	 * @param id The id of the customer which is to be updated
	 * @param username The new username of that customer
	 * @param password The new password of that customer
	 * @param name The new name of that customer
	 * @param location The new location of that customer
	 */
	@Modifying
	@Transactional
	@Query("update Customer x set x.username = ?2, x.password = ?3, x.name = ?4, x.location = ?5 where x.id = ?1")
	void setById(long id, String username, String password, String name, String location);
	
	/**
	 * Deletes the customer with the given id
	 * @param id The id of the customer which is deleted
	 */
	void deleteById(long id);
	
}
