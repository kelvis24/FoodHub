package foodhub.ioObjects;

/**
 * An input protocol used in adding an order
 * @author 1_CW_2
 *
 */
public class AddOrderInput extends Authentication {

	private OrderInfo data;
	
	/**
	 * Constructs a new order given enumerated information
	 * @param username The username of the customer adding the order
	 * @param password The password of the customer adding the order
	 * @param data The qualities of the order that is being added
	 * @see Authentication
	 */
	public AddOrderInput(String username, String password, OrderInfo data) {
		super(username, password);
		this.data = data;
	}
	
	/**
	 * A getter for the data field
	 * @return The qualities of the order that is being added
	 */
	public OrderInfo getData() {
		return data;
	}
	
}
