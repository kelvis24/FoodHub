package foodhub.ioObjects;

public class OrderInput {
	
	private String username;
	private String password;
	private Long orderId;

	public OrderInput(String username, String password, Long orderId) {
		this.username = username;
		this.password = password;
		this.orderId = orderId;
	}
		
	public OrderInput() {}
		
	public String getUsername() {
		return username;
	}
		
	public String getPassword() {
		return password;
	}
		
	public Long getOrderId() {
		return orderId;
	}
}
