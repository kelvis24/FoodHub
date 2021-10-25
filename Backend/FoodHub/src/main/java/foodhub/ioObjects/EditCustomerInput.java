package foodhub.ioObjects;

public class EditCustomerInput extends Authentication {
	
	private CustomerInfo data;

	public EditCustomerInput(String username, String password, CustomerInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public CustomerInfo getData() {
		return data;
	}
}
