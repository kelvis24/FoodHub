package foodhub.ioObjects;

import java.util.List;

import foodhub.database.Order;

public class OrderOutput {
	
	private long id;
	private String firm;
	private String customer;
	private String location;
	private int status;
	private double total;
	private List<OrderItemOutput> orderList;
	
	public OrderOutput(String firm, String customer, String location, Order order, List<OrderItemOutput> orderList) {
		this.id = order.getId();
		this.firm = firm;
		this.customer = customer;
		this.location = location;
		this.status = order.getStatus();
		this.orderList = orderList;
		this.total = 0;
		for (OrderItemOutput o : orderList) {
			total += o.getPrice();
		}
	}
	
	public OrderOutput(long id, String firm, String customer, String location, String title, int status, List<OrderItemOutput> orderList) {
		this.id = id;
		this.firm = firm;
		this.customer = customer;
		this.location = location;
		this.status = status;
		this.orderList = orderList;
		this.total = 0;
		for (OrderItemOutput o : orderList) {
			total += o.getPrice();
		}
	}
	
	public OrderOutput() {}
	
	public long getId() {
		return id;
	}
	
	public String getFirm() {
		return firm;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getStatus() {
		return status;
	}
	
	public double getTotal() {
		return total;
	}
	
	public List<OrderItemOutput> getOrderList() {
		return orderList;
	}

}
