package foodhub.ioObjects;

import java.util.List;

import foodhub.database.Order;

public class OrderOutput {
	
	private String firm;
	private String customer;
	private String title;
	private int status;
	private List<OrderItemOutput> orderList;
	
	public OrderOutput(String firm, String customer, Order order, List<OrderItemOutput> orderList) {
		this.firm = firm;
		this.customer = customer;
		this.title = order.getTitle();
		this.status = order.getStatus();
		this.orderList = orderList;
	}
	
	public OrderOutput(String firm, String customer, String title, int status, List<OrderItemOutput> orderList) {
		this.firm = firm;
		this.customer = customer;
		this.title = title;
		this.status = status;
		this.orderList = orderList;
	}
	
	public OrderOutput() {}
	
	public String getFirm() {
		return firm;
	}
	
	public String getCustomer() {
		return customer;
	}
	
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
