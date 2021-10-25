package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

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
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
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
    	List<Order> orders = orderRepository.findByCustomerId(user.getId());
    	for (Order o : orders) deleteOrder(o.getId());
    	customerRepository.deleteById(user.getId());
    	return new Message("success");
    }
    
    @PostMapping("/customers-get-orders")
    public List<OrderOutput> getOrders(@RequestBody Authentication body) {
    	List<OrderOutput> output = new ArrayList<OrderOutput>();
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword()))
        	return output;
    	List<Order> orders = orderRepository.findByFirmId(customer.getId());
    	for (Order order : orders) {
    		Firm firm = firmRepository.getById(order.getFirmId());
    		List<OrderItemOutput> orderList = new ArrayList<OrderItemOutput>();
    		List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
    		for (OrderItem orderItem : orderItems) {
    			Item item = itemRepository.findById(orderItem.getItemId());
    			orderList.add(new OrderItemOutput(orderItem, item));
    		}
    		output.add(new OrderOutput(firm.getUsername(), customer.getUsername(), order, orderList));
    	}
    	return output;
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
    	Firm firm = firmRepository.findById(body.getFirmId());
    	if (firm == null)
    		return new Message("failure","no such firm");
    	OrderInfo data = body.getData();
    	Order order = new Order(firm.getId(), customer.getId(), 0);
    	orderRepository.save(order);
    	List<Order> sameCustomer = orderRepository.findByCustomerId(customer.getId());
    	for (Order o : sameCustomer) {if (o.getFirmId()==firm.getId()) order = o;}
    	List<OrderItemInfo> list = data.getOrderList();
    	for (OrderItemInfo o : list) {
    		Item item = itemRepository.findById(o.getItemId());
        	if (item == null) {
        		deleteOrder(order.getId());
        		return new Message("failure","no such item");
        	}
        	OrderItem orderItem = new OrderItem(order.getId(), item.getId(), o.getQuantity(), o.getNotes());
        	orderItemRepository.save(orderItem);
    	}
    	return new Message("success");
    }
    
    @PostMapping("customers-remove-order")
    public Message removeOrder(@RequestBody RemoveEntity body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return new Message("failure","wrong username");
    	if (!customer.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	Order order = orderRepository.findById(body.getId());
    	if (order.getStatus() == 0)
    		return new Message("failure","not permitted");
    	deleteOrder(order.getId());
    	return new Message("success");
    }
    
    private void deleteOrder(long id) {
    	orderRepository.deleteById(id);
    	List<OrderItem> list = orderItemRepository.findByOrderId(id);
    	for (OrderItem o : list) orderItemRepository.deleteById(o.getId());
    }

}
