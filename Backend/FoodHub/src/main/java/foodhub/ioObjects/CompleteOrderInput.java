package foodhub.ioObjects;

public class CompleteOrderInput extends Authentication {

	private String customer;
	private String title;
	
	public CompleteOrderInput(String username, String password, String customer, String title) {
		super(username, password);
		this.customer = customer;
		this.title = title;
	}
	
	public CompleteOrderInput(String username, String password) {
		super(username, password);
	}
	
	public CompleteOrderInput() {
		super();
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public String getTitle() {
		return title;
	}
	
}
