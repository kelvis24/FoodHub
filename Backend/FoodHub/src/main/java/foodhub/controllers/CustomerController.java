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
	OrderItemsRepository orderItemsRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
    
    @PostMapping("/customer-login")
    public Message loginCustomer(@RequestBody Authentication body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return new Message("failure","wrong username");
    	if (!customer.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	return new Message("success");
    }
    
    @PostMapping("/customer-orders")
    public String customerOrders(@RequestBody Authentication body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword()))
    		return failure;
    	List<Order> orders = orderRepository.findByCustomerId(customer.getId());
    	List<OrderItems> orderItems = new ArrayList<OrderItems>();
    	for (Order o : orders) {
    		List<OrderItems> orderItemList = orderItemsRepository.findByOrderId(o.getId());
    		for (OrderItems i : orderItemList) {
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
    	OrderItems orderItem = new OrderItems(order.getId(), item.getId(), body.getQuantity(), body.getNotes());
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
