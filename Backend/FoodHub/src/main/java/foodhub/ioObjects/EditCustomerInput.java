package foodhub.ioObjects;

/**
 * An input protocol used in editing a customer
 * @author 1_CW_2
 */
public class EditCustomerInput extends Authentication {
	
	private CustomerInfo data;

	/**
	 * Constructs a new EditCustomerInput object given enumerated information
	 * @param username The username of the customer editing their data
	 * @param password The password fo the customer editing their data
	 * @param data The new qualities of that customer
	 * @see Authentication
	 */
	public EditCustomerInput(String username, String password, CustomerInfo data) {
		super(username, password);
		this.data = data;
	}
	
	/**
	 * A getter for the data field
	 * @return The new qualities of the customer
	 */
	public CustomerInfo getData() {
		return data;
	}
}
