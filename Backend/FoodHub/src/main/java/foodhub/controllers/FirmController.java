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

    @PostMapping(path = "/firms-create-category")
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
    
    @PostMapping("/edit-category")
    public Message editCategory(@RequestBody EditCategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	List<Category> sameFirm = categoryRepository.findByFirmId(firm.getId());
    	Category old = (Category)Entitled.findByTitle(sameFirm,  body.getSubject());
    	Category d = new Category(firm.getId(), body.getData());
    	
    	
    	
    	return new Message("success");
    }
    
    @PostMapping("/firms-remove-category")
    public Message removeCateogry(@RequestBody AddCategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findById(0);
    	if (category == null) {
    		return failure;
    	}
    	categoryRepository.deleteById(category.getId());
    	return success;
    }

    //Add item
    @PostMapping("/firms-create-item")
    public Message createItem(@RequestBody AddItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	List<Category> firmCats = categoryRepository.findByFirmId(firm.getId());
    	Category category = null;
    	for (Category c : firmCats) {
    		if (c.getTitle().equals(body.getCategory())) {
    			category = c;
    			break;
    		}
    	}
    	if (category == null) {
    		return failure;
    	}
    	Item item = body.getItem();
    	if (item == null) {
    		return failure;
    	}
    	List<Item> otherItems = itemRepository.findByFirmId(firm.getId());
    	for (Item i: otherItems) {
    		if (i.getTitle().equalsIgnoreCase(item.getTitle())) {
    			return failure;
    		}
    	}
    	item.setFirmId(firm.getId());
    	item.setCategoryId(category.getId());
    	itemRepository.save(item);
    	return success;
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
