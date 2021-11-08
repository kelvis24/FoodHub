package foodhub.ioObjects;

/**
 * An input protocol for a firm completing an order
 * @author 1_CW_2
 */
public class CompleteOrderInput extends Authentication {

	private long orderId;

	/**
	 * Constructs a new CompleteOrderInput given enumerated information
	 * @param username The username of the firm completing the order
	 * @param password The password of the firm completing the order
	 * @param orderId The id of the order which is to be completed
	 * @see Authentication
	 */
	public CompleteOrderInput(String username, String password, long orderId) {
		super(username, password);
		this.orderId = orderId;
	}
	
	/**
	 * A getter for the orderId field
	 * @return The id of the order that is completed
	 */
	public long getOrderId() {
		return orderId;
	}
	
}
