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
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	//TODO: Ensure this list is not null, as it is currently for some reason
    	Category sameTitle = (Category)Entitled.findByTitle(sameFirm, category.getTitle());
    	if (sameTitle != null)
    		return new Message("failure","title taken");
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
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category sameTitle = (Category)Entitled.findByTitle(sameFirm, d.getTitle());
    	if (sameTitle != null)
    		return new Message("failure","title taken");
    	Category old = (Category)Entitled.findByTitle(sameFirm,  body.getSubject());
    	if (old == null)
    		return new Message("failure","no such category");
    	categoryRepository.setById(old.getId(),d.getTitle(),d.getDescription());
    	return new Message("success");
    }
    
    @PostMapping("/firms-remove-category")
    public Message removeCateogry(@RequestBody RemoveEntitledInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category category = (Category)Entitled.findByTitle(sameFirm, body.getTitle());
    	if (category == null)
    		return new Message("failure","no such category");
    	categoryRepository.deleteById(category.getId());
    	List<Item> items = itemRepository.findByCategoryId(category.getId());
    	for (Item i : items) {
    		itemRepository.deleteById(i.getId());
    		List<OrderItem> orderItems = orderItemRepository.findByOrderId(i.getId());
    		for (OrderItem oi : orderItems) {
    			orderItemRepository.deleteById(oi.getId());
    			orderRepository.setById(oi.getOrderId(), 2);
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
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category category = (Category)Entitled.findByTitle(sameFirm, body.getTitle());
    	if (category == null)
    		return new Message("failure","no such category");
    	Item item = new Item(firm.getId(), category.getId(), body.getData());
    	List<Item> sameCategory = itemRepository.findByCategoryId(category.getId());
    	Item sameTitle = (Item)Entitled.findByTitle(sameCategory,  item.getTitle());
    	if (sameTitle != null)
    		return new Message("failure","title taken");
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
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category category = (Category)Entitled.findByTitle(sameFirm, body.getTitle());
    	if (category == null)
    		return new Message("failure","no such category");
    	List<Item> sameCategory = itemRepository.findByCategoryId(category.getId());
    	Item old = (Item)Entitled.findByTitle(sameCategory,  body.getTitle());
    	if (old == null)
    		return new Message("failure","no such item");
    	itemRepository.setById(old.getId(), d.getTitle(), d.getDescription(), d.getPrice());
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(old.getId());
		for (OrderItem oi : orderItems) {
			orderRepository.setById(oi.getOrderId(), 2);
		}
    	return new Message("success");
    }
    
    @PostMapping("/firms-remove-item")
    public Message removeItem(@RequestBody RemoveItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category category = (Category)Entitled.findByTitle(sameFirm, body.getCategoryTitle());
    	if (category == null)
    		return new Message("failure","no such category");
    	List<Item> sameCategory = itemRepository.findByCategoryId(category.getId());
    	Item item = (Item)Entitled.findByTitle(sameCategory,  body.getItemTitle());
    	itemRepository.deleteById(item.getId());
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(item.getId());
		for (OrderItem oi : orderItems) {
			orderItemRepository.deleteById(oi.getId());
			orderRepository.setById(oi.getOrderId(), 2);
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
		Customer customer = customerRepository.findByUsername(body.getCustomer());
		if (customer == null)
			return new Message("failure","no such customer");
    	List<Order> sameCustomer = orderRepository.findByCustomerId(customer.getId());
    	Order order = (Order)Entitled.findByTitle(sameCustomer,  body.getTitle());
    	if (order.getFirmId() != firm.getId())
    		return new Message("failure","erroneous behaviour");
    	if (order.getStatus() != 0)
    		return new Message("failure","invalid operation");
    	orderRepository.setById(order.getId(), 1);
		return new Message("success");
    }
    
}
