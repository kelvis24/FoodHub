package foodhub.ioObjects;

public class EditCustomerInput extends Authentication {
	
	private CustomerInfo data;

	public EditCustomerInput(String username, String password, CustomerInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public EditCustomerInput(String username, String password) {
		super(username, password);
	}
	
	public EditCustomerInput() {
		super();
	}
	
	public CustomerInfo getData() {
		return data;
	}
}
