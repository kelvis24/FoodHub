package foodhub.ioObjects;

public class CompleteOrderInput extends Authentication {

	private long orderId;
	
	public CompleteOrderInput(String username, String password, long orderId) {
		super(username, password);
		this.orderId = orderId;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
}
