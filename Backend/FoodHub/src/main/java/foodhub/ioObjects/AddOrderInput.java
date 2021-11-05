package foodhub.ioObjects;

public class AddOrderInput extends Authentication {

	private OrderInfo data;
	
	public AddOrderInput(String username, String password, OrderInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public OrderInfo getData() {
		return data;
	}
	
}
