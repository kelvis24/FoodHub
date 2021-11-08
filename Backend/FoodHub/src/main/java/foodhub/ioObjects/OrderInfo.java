package foodhub.ioObjects;

import java.util.List;

/**
 * An input protocol for specifying the qualities of an order
 * @author 1_CW_2
 */
public class OrderInfo {

	private long firmId;
	private List<OrderItemInfo> orderList;

	/**
	 * Constructs an OrderInfo object from enumerated information
	 * @param firmId The id of the firm to which the order belongs
	 * @param orderList A list of qualities of OrderItem relations of the order
	 */
	public OrderInfo(long firmId, List<OrderItemInfo> orderList) {
		this.firmId = firmId;
		this.orderList = orderList;
	}
	
	/**
	 * A getter for the firmId field
	 * @return The id of the firm of the order
	 */
	public long getFirmId() {
		return firmId;
	}
	
	/**
	 * A getter for the orderList field
	 * @return A list of qualities of OrderItem relations of the order
	 */
	public List<OrderItemInfo> getOrderList() {
		return orderList;
	}
	
}
