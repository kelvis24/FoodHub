package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

@RestController
public class FirmController {
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemsRepository orderItemsRepository;

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
    	categoryRepository.setById(old.getId(),firm.getId(),d.getTitle(),d.getDescription());
    	return new Message("success");
    }
    
    // TODO deal with cascading effects of deleting categories
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
    public Message editItem(@RequestBody AddItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Item item = itemRepository.findById(0);
    	if (item == null) {
    		return failure;
    	}
    	item.setTitle(body.getNewItem().getTitle());
    	item.setDescription(body.getNewItem().getDescription());
    	item.setPrice(body.getNewItem().getPrice());
    	
    	return success;
    }
    
    @PostMapping("/firms-remove-item")
    public Message removeItem(@RequestBody AddItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findById(0);
    	if (category == null) {
    		return failure;
    	}
    	Item item = itemRepository.findById(0);
    	if (item == null) {
    		return failure;
    	}
    	itemRepository.deleteById(item.getId());
    	return success;
    }
    
    @PostMapping("/firms-get-orders")
    public List<OrderInfo> showOrders(@RequestBody Authentication body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	List<Order> orders = orderRepository.findByFirmId(firm.getId());
    	List<OrderItems> orderItems = new ArrayList<OrderItems>();
    	for (Order o : orders) {
    		List<OrderItems> orderItemList = orderItemsRepository.findByOrderId(o.getId());
    		for (OrderItems i : orderItemList) {
    			orderItems.add(i);
    		}
    	}
    	return orderItems.toString();
    }
    
}
