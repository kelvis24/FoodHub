package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/customers")
    public List<Customer> listCustomers() {
    	return customerRepository.findAll();
    }

    @PostMapping("/customers")
    public String createCustomer(@RequestBody Customer customer) {
    	if (customer == null)
    		return failure;
    	Customer sameEmail = customerRepository.findByUsername(customer.getUsername());
    	if (sameEmail != null)
    		return failure;
    	customerRepository.save(customer);
    	return success;
    }
    
    @PostMapping("/customers-login")
    public String loginCustomer(@RequestBody LoginInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword()))
    		return failure; 
    	return success;
    }
    
    @PostMapping("/view-order")
    public String viewOrder(@RequestBody OrderInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword())) {
    		return failure;
    	}
    	Optional<Order> optionalOrder = orderRepository.findById(body.getOrderId());
    	Order order = optionalOrder.get();
    	if (order == null || order.getCustomerId() != customer.getId()) {
    		return failure;
    	}
    	List<OrderItems> orderItems = orderItemsRepository.findByOrderId(order.getId());
    	return orderItems.toString();
    	}
    
    @PostMapping("/add-item")
    public String addItemToOrder(@RequestBody OrderInput body) {
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
}
