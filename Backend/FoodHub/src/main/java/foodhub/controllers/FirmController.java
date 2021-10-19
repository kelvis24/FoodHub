package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.CategoryInput;
import foodhub.ioObjects.ItemInput;
import foodhub.ioObjects.LoginInput;

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

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	private String errorUser = "{\"message\":\"user not defined/found\"}";

    @GetMapping("/firms")
    public List<Firm> listFirms() {
    	return firmRepository.findAll();
    }

    @PostMapping(path = "/firms-create-categories")
    public String createFirm(@RequestBody CategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = body.getCategory();
    	if (category == null)
    		return failure;
    	List<Category> otherCats = categoryRepository.findByFirmId(firm.getId());
    	for (Category c: otherCats) {
    		if (c.getTitle().equalsIgnoreCase(category.getTitle())) {
    			return failure;
    		}
    	}
    	category.setFirmId(firm.getId());
    	categoryRepository.save(category);
    	return success;
    }
    
    @PostMapping("remove-category")
    public String removeCateogry(@RequestBody CategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategoryTitle());
    	if (category == null) {
    		return failure;
    	}
    	categoryRepository.deleteById(category.getId());
    	return success;
    }
    
    @PostMapping("/edit-category")
    public String editCategory(@RequestBody CategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategory().getTitle());
    	if (category == null) {
    		return failure;
    	}
    	category.setTitle(body.getNewCategory().getTitle());
    	category.setDescription(body.getNewCategory().getDescription());
    	return success;
    }
    
    //Work on
    @PostMapping("/categories")
    public List<Category> listCategories(Model model) {
    	return categoryRepository.findAll();
    }

    //Add item
    @PostMapping("/create-item")
    public String createItem(@RequestBody ItemInput body) {
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
    
    @PostMapping("/remove-item")
    public String removeItem(@RequestBody ItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategory());
    	if (category == null) {
    		return failure;
    	}
    	Item item = itemRepository.findByTitle(body.getItemTitle());
    	if (item == null) {
    		return failure;
    	}
    	itemRepository.deleteById(item.getId());
    	return success;
    }
    
    @PostMapping("/edit-item")
    public String editItem(@RequestBody ItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Item item = itemRepository.findByTitle(body.getItem().getTitle());
    	if (item == null) {
    		return failure;
    	}
    	item.setTitle(body.getNewItem().getTitle());
    	item.setDescription(body.getNewItem().getDescription());
    	item.setPrice(body.getNewItem().getPrice());
    	
    	return success;
    }
    
    @PostMapping("/show-orders")
    public String showOrders(@RequestBody LoginInput body) {
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
