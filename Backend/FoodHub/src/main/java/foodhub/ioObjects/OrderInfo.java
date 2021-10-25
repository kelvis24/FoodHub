package foodhub.ioObjects;

import java.util.List;

public class OrderInfo {

	private String title;
	private List<OrderItemInfo> orderList;

	public OrderInfo(String title, List<OrderItemInfo> orderList) {
		this.title = title;
		this.orderList = orderList;
	}

	public OrderInfo() {}

	public String getTitle() {
		return title;
	}
	
	public List<OrderItemInfo> getOrderList() {
		return orderList;
	}
	
}
