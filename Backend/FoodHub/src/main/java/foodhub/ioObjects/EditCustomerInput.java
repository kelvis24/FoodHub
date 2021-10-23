package foodhub.ioObjects;
import foodhub.database.Customer;

public class EditCustomerInput {
	private String username;
	private String password;
	private Customer customer;
	
	public EditCustomerInput(String username, String password, Customer customer) {
		this.username = username;
		this.password = password;
		this.customer = customer;
	}
	
	public EditCustomerInput() {}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Customer getCustomer() {
		return customer;
	}
	
}
