package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderItemRepository orderItemsRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	
	@PostMapping("/customers-authenticate")
	public Message authenticateCustomer(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	return new Message("success");
	}
    
    @PostMapping("/customer-orders")
    public String customerOrders(@RequestBody Authentication body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword()))
    		return failure;
    	List<Order> orders = orderRepository.findByCustomerId(customer.getId());
    	List<OrderItem> orderItems = new ArrayList<OrderItem>();
    	for (Order o : orders) {
    		List<OrderItem> orderItemList = orderItemsRepository.findByOrderId(o.getId());
    		for (OrderItem i : orderItemList) {
    			orderItems.add(i);
    		}
    	}
    	return orderItems.toString();
    }
    
    @PostMapping("/add-item")
    public String addItemToOrder(@RequestBody AddOrderInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword())) {
    		return failure;
    	}
    	Optional<Order> optionalOrder = orderRepository.findById(body.getOrderId());
    	Order order = optionalOrder.get();
    	if (order == null || order.getCustomerId() != customer.getId()) {
    		return failure;
    	}
    	Item item = body.getItem();
    	if (item == null) {
    		return failure;
    	}
    	OrderItem orderItem = new OrderItem(order.getId(), item.getId(), body.getQuantity(), body.getNotes());
    	orderItemsRepository.save(orderItem);
    	return success;
    }
    
    @PostMapping("customer-change-info")
    public String changeInformation(@RequestBody EditCustomerInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword())) {
    		return failure;
    	}
    	// customer.setName(body.getCustomer().getName());
    	// customer.setUsername(body.getCustomer().getUsername());
    	// customer.setPassword(body.getCustomer().getPassword());
    	// customer.setLocation(body.getCustomer().getLocation());
    	return success;
    }

}
