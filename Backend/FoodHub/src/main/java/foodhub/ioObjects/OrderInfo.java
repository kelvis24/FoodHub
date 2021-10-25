package foodhub.ioObjects;

import java.util.List;

public class OrderInfo {

	private long firmId;
	private List<OrderItemInfo> orderList;

	public OrderInfo(long firmId, List<OrderItemInfo> orderList) {
		this.firmId = firmId;
		this.orderList = orderList;
	}
	
	public long getFirmId() {
		return firmId;
	}
	
	public List<OrderItemInfo> getOrderList() {
		return orderList;
	}
	
}
