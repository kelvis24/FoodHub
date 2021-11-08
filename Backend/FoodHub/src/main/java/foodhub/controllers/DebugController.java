package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

/**
 * The Debug commands for testing the Backend of FoodHub
 *     DO NOT USE
 *     REMOVE BEFORE DEPLOYMENT
 * @author 1_CW_2
 *
 */
@RestController
public class DebugController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FirmRepository firmRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemsRepository;
    
	/**
	 * Sets an Owner into the Admin repository with basic information.
	 * @return a success Message 
	 */
    @GetMapping("/debug-default-owner")
    public Message defaultOwner() {
    	Admin owner = new Admin("arvidg@iastate.edu","aA0/aaaaaaaa","Arvid",1);
    	adminRepository.save(owner);
        return new Message("success");
    }
    
    /**
     * Edits an already existing Admin with new information (except for ID, which remains constant)
     * @param body an Admin entity with all Admin information filled (username, name, ect.)
     * @return a success Message
     * @see Admin
     */
    @PostMapping("/debug-edit-admin")
    public Message editAdmin(@RequestBody Admin body) {
    	Admin old = adminRepository.findByUsername(body.getUsername());
    	adminRepository.setById(old.getId(), body.getUsername(), body.getPassword(), body.getName(), body.getType());
    	return new Message("success");
    }
        
    /**
     * Deletes an Admin from the Admin repository.
     * @param body the Username of the Admin to be deleted
     * @return a success Message
     */
    @PostMapping("/debug-delete-admin")
    public Message deleteAdmin(@RequestBody Username body) {
    	Admin old = adminRepository.findByUsername(body.getUsername());
    	adminRepository.deleteById(old.getId());
    	return new Message("success");
    }
    
    /**
     * Lists all Admins in the Admin repository.
     * @return a List of Admins. Can be empty.
     */
    @GetMapping("/debug-get-admins")
    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }
    
    /**
     * Lists all Customers in the Customer repository
     * @return a List of Customers. Can be empty.
     */
    @GetMapping("/debug-get-customers")
    public List<Customer> listCustomers() {
    	return customerRepository.findAll();
    }

    /**
     * Lists all Firms in the Firm repository.
     * @return a List of Firms. Can be empty.
     */
    @GetMapping("/debug-get-firms")
    public List<Firm> listFirms() {
    	return firmRepository.findAll();
    }
	
    /**
     * Lists all Categories within the Category repository.
     * @return a List of Categories. Can be empty.
     */
    @GetMapping("/debug-get-categories")
    public List<Category> listCategories() {
    	return categoryRepository.findAll();
    }
	
    /**
     * Lists all Items within the Item repository.
     * @return a List of Items. Can be empty.
     */
    @GetMapping("/debug-get-items")
    public List<Item> listItems() {
    	return itemRepository.findAll();
    }
	
    /**
     * Lists all Orders within the Order repository
     * @return a List of Orders. Can be empty.
     */
    @GetMapping("/debug-get-orders")
    public List<Order> listOrders() {
    	return orderRepository.findAll();
    }
	
    /**
     * Lists all OrderItems in the OrderItems repository.
     * @return a List of OrderItems. Can be empty. 
     */
    @GetMapping("/debug-get-orderitems")
    public List<OrderItem> listOrderItems() {
    	return orderItemsRepository.findAll();
    }
	
}
