package foodhub.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Specifies the operations to be enacted upon the table of OrderItem relations
 * @author 1_CW_2
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	/**
	 * Returns a list of all OrderItem relations
	 * @return a list of all OrderItem relations
	 */
	List<OrderItem> findAll();
	
	/**
	 * Returns the OrderItem relation of the given id
	 * @param id The id of the OrderItem relation
	 * @return The OrderItem relation of the id
	 */
	OrderItem findById(long id);
	
	/**
	 * Returns the list of OrderItem relations that belong to the order with the given id
	 * @param orderId The id of the order to which the returned list of OrderItem relations belong
	 * @return The list of OrderItem relations that belong to the order with the given id
	 */
	List<OrderItem> findByOrderId(long orderId);
	
	/**
	 * Returns the list of OrderItem relations that relate to the item with the given id
	 * @param itemId The id of the item to which the returned list of OrderItem relations relate
	 * @return The list of OrderItem relations that relate to the item with the given id
	 */
	List<OrderItem> findByItemId(long itemId);
	
	/**
	 * Deletes the OrderItem relation with the given id
	 * @param id The id of the OrderItem relation that is deleted
	 */
	void deleteById(int id);
	
}