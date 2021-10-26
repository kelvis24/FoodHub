package foodhub.ioObjects;

import java.util.List;

import foodhub.database.Order;

public class OrderOutput {
	
	private String firm;
	private String customer;
	private int status;
	private double total;
	private List<OrderItemOutput> orderList;
	
	public OrderOutput(String firm, String customer, Order order, List<OrderItemOutput> orderList) {
		this.firm = firm;
		this.customer = customer;
		this.status = order.getStatus();
		this.orderList = orderList;
		this.total = 0;
		for (OrderItemOutput o : orderList) {
			total += o.getPrice();
		}
	}
	
	public OrderOutput(String firm, String customer, String title, int status, List<OrderItemOutput> orderList) {
		this.firm = firm;
		this.customer = customer;
		this.status = status;
		this.orderList = orderList;
		this.total = 0;
		for (OrderItemOutput o : orderList) {
			total += o.getPrice();
		}
	}
	
	public OrderOutput() {}
	
	public String getFirm() {
		return firm;
	}
	
	public String getCustomer() {
		return customer;
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
