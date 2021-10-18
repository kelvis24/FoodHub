package foodhub.controllers;

import java.util.ArrayList;
import java.util.Iterator;
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
	OrderItemsRepository orderItemsRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
    
    @GetMapping("/debug-default-owner")
    public String defaultOwner() {
    	Admin owner = new Admin("arvid","arvidg@iastate.edu","aA0/aaaaaaaa",1);
    	adminRepository.save(owner);
        return success;
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
    public List<OrderItems> listOrderItems() {
    	return orderItemsRepository.findAll();
    }
	
}
