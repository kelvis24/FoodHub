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
public class FirmController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@PostMapping("/firms-authenticate")
	public Message authenticateFirm(@RequestBody Authentication body) {
    	Firm user = firmRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	return new Message("success");
	}

    @PostMapping("/firms-create-category")
    public Message createCategory(@RequestBody AddCategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Category category = new Category(firm.getId(), body.getData());
    	categoryRepository.save(category);
    	return new Message("success");
    }
    
    @PostMapping("/firms-edit-category")
    public Message editCategory(@RequestBody EditCategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Category d = new Category(firm.getId(), body.getData());
    	Category old = categoryRepository.findById(body.getCategoryId());
    	if (old == null)
    		return new Message("failure","no such category");
    	categoryRepository.setById(old.getId(),d.getTitle(),d.getDescription());
    	return new Message("success");
    }
    
    @PostMapping("/firms-remove-category")
    public Message removeCateogry(@RequestBody RemoveEntity body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	Category category = categoryRepository.findById(body.getId());
    	if (category == null)
    		return new Message("failure","no such category");
    	categoryRepository.deleteById(category.getId());
    	List<Item> items = itemRepository.findByCategoryId(category.getId());
    	for (Item i : items) {
    		itemRepository.deleteById(i.getId());
    		List<OrderItem> orderItems = orderItemRepository.findByOrderId(i.getId());
    		for (OrderItem oi : orderItems) {
    			orderItemRepository.deleteById(oi.getId());
    			Order order = orderRepository.findById(oi.getOrderId());
    			if (order.getStatus() != 1)
    				orderRepository.setById(order.getId(), 2);
    		}
    	}
    	return new Message("success");
    }

    @PostMapping("/firms-create-item")
    public Message createItem(@RequestBody AddItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Category category = categoryRepository.findById(body.getCategoryId());
    	if (category == null)
    		return new Message("failure","no such category");
    	Item item = new Item(firm.getId(), category.getId(), body.getData());
    	itemRepository.save(item);
    	return new Message("success");
    }
    
    @PostMapping("/firms-edit-item")
    public Message editItem(@RequestBody EditItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	ItemInfo d = body.getData();
    	Item item = itemRepository.findById(body.getItemId());
    	if (item == null)
    		return new Message("failure","no such item");
    	itemRepository.setById(body.getItemId(), d.getTitle(), d.getDescription(), d.getPrice());
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(body.getItemId());
		for (OrderItem oi : orderItems) {
			Order order = orderRepository.findById(oi.getOrderId());
			if (order.getStatus() != 1)
				orderRepository.setById(order.getId(), 2);
		}
    	return new Message("success");
    }
    
    @PostMapping("/firms-remove-item")
    public Message removeItem(@RequestBody RemoveEntity body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	Item item = itemRepository.findById(body.getId());
    	if (item == null)
    		return new Message("failure","no such item");
    	itemRepository.deleteById(item.getId());
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(item.getId());
		for (OrderItem oi : orderItems) {
			orderItemRepository.deleteById(oi.getId());
			Order order = orderRepository.findById(oi.getOrderId());
			if (order.getStatus() != 1)
				orderRepository.setById(order.getId(), 2);
		}
    	return new Message("success");
    }
    
    @PostMapping("/firms-get-orders")
    public List<OrderOutput> getOrders(@RequestBody Authentication body) {
    	List<OrderOutput> output = new ArrayList<OrderOutput>();
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
        	return output;
    	List<Order> orders = orderRepository.findByFirmId(firm.getId());
    	for (Order order : orders) {
    		Customer customer = customerRepository.getById(order.getCustomerId());
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
    
    @PostMapping("/firms-complete-order")
    public Message completeOrder(@RequestBody CompleteOrderInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
			return new Message("failure","wrong username");
		if (!firm.getPassword().equals(body.getPassword()))
	    	return new Message("failure","wrong password");
    	Order order = orderRepository.findById(body.getOrderId());
    	if (order.getFirmId() != firm.getId())
    		return new Message("failure","erroneous behaviour");
    	if (order.getStatus() != 0)
    		return new Message("failure","invalid operation");
    	orderRepository.setById(order.getId(), 1);
		return new Message("success");
    }
    
}
