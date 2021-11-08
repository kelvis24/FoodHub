package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Specifies the operations enacted upon the table of orders
 * @author 1_CW_2
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/**
	 * Returns a list of all orders
	 */
	List<Order> findAll();
	
	/**
	 * Returns the order of the given id
	 * @param id The id of the order that is returned
	 * @return The order of the given id
	 */
	Order findById(long id);

	/**
	 * Returns the list of orders that are placed by the customer with the given id
	 * @param customerId The id of the customer that placed the orders returned
	 * @return The list of orders that are placed by the customer with the given id
	 */
	List<Order> findByCustomerId(long customerId);
	
	/**
	 * Returns the list of orders that are placed to the firm with the given id
	 * @param firmId The id of the firm to which the orders returned are placed
	 * @return The list of orders that are placed to the firm with the given id
	 */
	List<Order> findByFirmId(long firmId);

	/**
	 * Updates the status of the order with the given id
	 * @param id The id of the order that will be updated
	 * @param status The new status of the order
	 */
	@Modifying
	@Transactional
	@Query("update Order x set x.status = ?2 where x.id = ?1")
	void setById(long id, int status);
	
	/**
	 * Deletes the order with the given id
	 * @param id The id of the order that will be deleted
	 */
	void deleteById(int id);
	
}