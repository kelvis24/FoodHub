package foodhub.ioObjects;

public class AddOrderInput extends Authentication {

	private String firm;
	private OrderInfo data;
	
	public AddOrderInput(String username, String password, String firm, OrderInfo data) {
		super(username, password);
		this.firm = firm;
		this.data = data;
	}
	
	public AddOrderInput(String username, String password) {
		super(username, password);
	}
	
	public AddOrderInput() {
		super();
	}
	
	public String getFirm() {
		return firm;
	}
	
	public OrderInfo getData() {
		return data;
	}
	
}
