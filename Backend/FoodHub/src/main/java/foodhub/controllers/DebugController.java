package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

@RestController
public class DebugController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemsRepository;
    
    @GetMapping("/debug-default-owner")
    public Message defaultOwner() {
    	Admin owner = new Admin("arvidg@iastate.edu","aA0/aaaaaaaa","Arvid",1);
    	adminRepository.save(owner);
        return new Message("success");
    }
    
    @PostMapping("/debug-edit-admin")
    public Message editAdmin(@RequestBody Admin body) {
    	Admin old = adminRepository.findByUsername(body.getUsername());
    	adminRepository.setById(old.getId(), body.getUsername(), body.getPassword(), body.getName(), body.getType());
    	return new Message("success");
    }
        
    @PostMapping("/debug-delete-admin")
    public Message deleteAdmin(@RequestBody Username body) {
    	Admin old = adminRepository.findByUsername(body.getUsername());
    	adminRepository.deleteById(old.getId());
    	return new Message("success");
    }
    
    @GetMapping("/debug-get-admins")
    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }
    
    @GetMapping("/debug-get-customers")
    public List<Customer> listCustomers() {
    	return customerRepository.findAll();
    }

    @GetMapping("/debug-get-firms")
    public List<Firm> listFirms() {
    	return firmRepository.findAll();
    }
	
    @GetMapping("/debug-get-categories")
    public List<Category> listCategories() {
    	return categoryRepository.findAll();
    }
	
    @GetMapping("/debug-get-items")
    public List<Item> listItems() {
    	return itemRepository.findAll();
    }
	
    @GetMapping("/debug-get-orders")
    public List<Order> listOrders() {
    	return orderRepository.findAll();
    }
	
    @GetMapping("/debug-get-orderitems")
    public List<OrderItem> listOrderItems() {
    	return orderItemsRepository.findAll();
    }
	
}
