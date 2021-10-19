package foodhub.ioObjects;
import foodhub.database.Customer;

public class CustomerInput {
	private String username;
	private String password;
	private Customer customer;
	
	public CustomerInput(String username, String password, Customer customer) {
		this.username = username;
		this.password = password;
		this.customer = customer;
	}
	
	public CustomerInput() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
