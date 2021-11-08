package foodhub.ioObjects;

import java.util.List;

import foodhub.database.Order;

/**
 * An output protocol for specifying the qualities of an order
 * @author 1_CW_2
 *
 */
public class OrderOutput {
	
	private long id;
	private String firm;
	private String customer;
	private String location;
	private int status;
	private double total;
	private List<OrderItemOutput> orderList;
	
	/**
	 * Constructs an OutputOrder object from an Order entity from the database and other information
	 * @param firm The name of the firm to which the order is placed
	 * @param customer The name of the customer from which the order is placed
	 * @param location The location of the customer from which the order is placed
	 * @param order The corresponding order entity, from the database
	 * @param orderList The qualities of the order's OrderItem relations
	 */
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
	
	/**
	 * Constructs an OutputOrder object from enumerated information
	 * @param id The id of the order
	 * @param firm The name of the firm to which the order is placed
	 * @param customer The name of the customer from which the order is placed
	 * @param location The location of the customer from which the order is placed
	 * @param title UNUSED A carry over from an old design
	 * @param status The initial status of the order
	 * @param orderList The qualities of the order's OrderItem relations
	 */
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
	
	/**
	 * A default constructor
	 */
	public OrderOutput() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the order
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * A getter for the firm field
	 * @return The name of the firm to which the order is placed
	 */
	public String getFirm() {
		return firm;
	}
	
	/**
	 * A getter for the customer field
	 * @return The name of the customer from which the order is placed
	 */
	public String getCustomer() {
		return customer;
	}
	
	/**
	 * A getter for the location field
	 * @return The location of the customer from which the order is placed
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * A getter for the status field
	 * @return The status of the customer from which the order is placed
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * A getter for the total field
	 * @return The total price of the order; the sum of all item's prices respecting their quantities
	 */
	public double getTotal() {
		return total;
	}
	
	/**
	 * A getter for the orderList field
	 * @return The qualities of this order's OrderItem relations
	 */
	public List<OrderItemOutput> getOrderList() {
		return orderList;
	}

}
