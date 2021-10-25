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
	FirmRepository firmRepository;

	@Autowired
	OrderItemRepository orderItemsRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/customers-authenticate")
	public Message authenticateCustomer(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	return new Message("success");
	}
    
    @PostMapping("customers-edit-customer")
    public Message createCustomer(@RequestBody EditCustomerInput body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	CustomerInfo d = body.getData();
    	Customer sameUsername = customerRepository.findByUsername(d.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","username taken");
    	customerRepository.setById(user.getId(),d.getUsername(),d.getPassword(),d.getName(),user.getLocation());
    	return new Message("success");
    }
    
    @PostMapping("customers-remove-customer")
    public Message removeCustomer(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	customerRepository.deleteById(user.getId());
    	return new Message("success");
    }
    
    @PostMapping("/customers-create-order")
    public Message customerOrders(@RequestBody AddOrderInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return new Message("failure","wrong username");
    	if (!customer.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Firm firm = firmRepository.findByUsername(body.getFirm());
    	if (firm == null)
    		return new Message("failure","no such firm");
    	OrderInfo data = body.getData();
    	Order order = new Order(firm.getId(), customer.getId(), data.getTitle(), 0);
    	
    	
    	
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

}
