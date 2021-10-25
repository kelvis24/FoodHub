package foodhub.ioObjects;

public class AddOrderInput extends Authentication {

	private long firmId;
	private OrderInfo data;
	
	public AddOrderInput(String username, String password, long firmId, OrderInfo data) {
		super(username, password);
		this.firmId = firmId;
		this.data = data;
	}
	
	public long getFirmId() {
		return firmId;
	}
	
	public OrderInfo getData() {
		return data;
	}
	
}
