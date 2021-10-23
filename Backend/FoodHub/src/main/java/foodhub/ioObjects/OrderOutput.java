package foodhub.ioObjects;

import java.util.List;

import foodhub.database.Order;

public class OrderOutput {
	
	private String title;
	private int status;
	private List<OrderItemOutput> orderList;
	
	public OrderOutput(Order order, List<OrderItemOutput> orderList) {
		this.title = order.getTitle();
		this.status = order.getStatus();
		this.orderList = orderList;
	}
	
	public OrderOutput(String title, int status, List<OrderItemOutput> orderList) {
		this.title = title;
		this.status = status;
		this.orderList = orderList;
	}
	
	public OrderOutput() {}
	
	public String getTitle() {
		return title;
	}
	
	public int getStatus() {
		return status;
	}
	
	public List<OrderItemOutput> getOrderList() {
		return orderList;
	}

}
