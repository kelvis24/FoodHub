package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

/**
 * The main controller for all Admin backend methods.
 * @author 1_CW_2 
 *
 */
@RestController
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private FirmRepository firmRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/**
	 * Returns whether or not the given credentials are a valid admin or owner.
	 * Always returns a Message entity, either the type of Admin logged in on success or
	 * a failure with error on failing to authorize the Admin. Errors include wrong username and
	 * wrong password. 
	 * @param body an Authentication for an Admin of any type. 
	 * @return A message with the type of Admin (either admin or owner)
	 * @see Authentication
	 */
	@PostMapping("/admins-authenticate")
	public Message authenticateAdmin(@RequestBody Authentication body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	return new Message(user.getType() == 0 ? "admin" : "owner");
	}
	
	/**
	 * Returns a list of all Admins currently saved within the Admin repository. 
	 * This list can be empty, and will be so if the Admin calling it is either
	 * incorrectly logged in (wrong username or password) or is not an Owner. 
	 * @param body Authentication for an Admin of any type.
	 * @return A List with type AdminOutput of all Admins currently saved within the Admin repository.
	 * @see Authentication 
	 */
    @PostMapping("/admins-get-admins")
    public List<AdminOutput> getAdmins(@RequestBody Authentication body) {
    	List<AdminOutput> output = new ArrayList<AdminOutput>();
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return output;
    	List<Admin> admins = adminRepository.findAll();
    	for (Admin a : admins) {output.add(new AdminOutput(a));}
    	return output;
    }
    
    /**
     * Allows an Owner to create a new Admin.
     * This method will always return a Message, either a success based on correct implementation
     * or a failure with error following failure. Errors include wrong username, wrong password, not
     * being an Owner, the added Admin body not existing (null), or the Admin username already existing
     * within the Admin repository. 
     * @param body AddAdminInput, includes owner login and AdminInfo
     * @return Message with the status of creation (success/failure with error)
     * @see AddAdminInput
     */
    @PostMapping("/admins-create-admin")
    public Message createAdmin(@RequestBody AddAdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (user.getType() != 1)
    		return new Message("failure","Wrong Credentials");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	Admin admin = new Admin(body.getData());
    	Admin sameUsername = adminRepository.findByUsername(admin.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","Username Taken");
    	adminRepository.save(admin);
    	return new Message("success");
    }
    
    /**
     * Allows an Owner to edit Admin information in the Admin Repository.
     * This method will always return a Message, either a success based on correct implementation
     * or a failure with error following failure. Errors include wrong username, wrong password, not
     * being an Owner, the edit Admin body not existing (null), the new Admin username already existing
     * within the Admin repository, or the edit Admin not existing within the repository. 
     * @param body An EditAdminInput, which includes owner login, AdminInfo, and String with subject
     * @return Message with status of edit (success/failure with error)
     * @see EditAdminInput
     */
    @PostMapping("/admins-edit-admin")
    public Message editAdmin(@RequestBody EditAdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (user.getType() != 1)
    		return new Message("failure","Wrong Credentials");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	Admin old = adminRepository.findById(body.getAdminId());
    	if (old == null)
    		return new Message("failure","No Such User");
    	AdminInfo d = body.getData();
    	Admin sameUsername = adminRepository.findByUsername(d.getUsername());
    	if (sameUsername != null && !sameUsername.getUsername().equals(old.getUsername()))
    		return new Message("failure","Username Taken");
    	adminRepository.setById(old.getId(),d.getUsername(),d.getPassword(),d.getName(),old.getType());
    	return new Message("success");
    }
    
    /**
     * Allows an Owner to remove any Admin from the repository.
     * Will always return a message after execution, either a success following correct information
     * being given or a failure with an error explaining why. Errors include wrong username, wrong password,
     * inputted Admin login not belonging to an owner, the Admin information not connected to an Admin in the repository,
     * or trying to delete another owner.
     * @param body RemoveEntity, which uses login information and the ID of the removed entity
     * @return Message with status of removal (success/failure with error)
     * @see RemoveEntity
     */
    @PostMapping("admins-remove-admin")
    public Message removeAdmin(@RequestBody RemoveEntity body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (user.getType() != 1)
    		return new Message("failure","Wrong Credentials");
    	Admin admin = adminRepository.findById(body.getId());
    	if (admin == null)
    		return new Message("failure","No Such User");
    	if (admin.getType() == 1)
    		return new Message("failure","Not Permitted");
    	adminRepository.deleteById(admin.getId());
    	return new Message("success");
    }

    /**
     * Allows any Admin to create a new Firm in the Firm repository.
     * Will always return a message after execution, either a success following correct information
     * being given or a failure with an error explaining why. Errors include wrong username, wrong password,
     * no firm data supplied (null), or the new firm name already taken within the firm repository.
     * @param body AddFirmInput, including Admin login details and Firm details
     * @return  Message with status of creation (success/failure with error)
     * @see AddFirmInput
     */
    @PostMapping("/admins-create-firm")
    public Message createFirm(@RequestBody AddFirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	Firm firm = new Firm(body.getData());
    	Firm sameUsername = firmRepository.findByUsername(firm.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","Username Taken");
    	firmRepository.save(firm);
    	return new Message("success");
    }
    
    /**
     * Allows an Admin of any type edit a Firm within the Firm repository.
     * Will always return a message after execution, either a success following correct information
     * being given or a failure with an error explaining why. Errors includ wrong username, wrong password,
     * no firm data supplied (null), the editted firm name already taken within the firm repository, or the 
     * editted firm not existing in the firm repository. 
     * @param body an EditFirmInput class with login credentials, Firm data, and Firm ID
     * @return a Message with status of the edit (success/failure with error) 
     * @see EditFirmInput
     */
    @PostMapping("/admins-edit-firm")
    public Message editFirm(@RequestBody EditFirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	Firm old = firmRepository.findById(body.getFirmId());
    	if (old == null)
    		return new Message("failure","No Such User");
    	FirmInfo d = body.getData();
    	Firm sameUsername = firmRepository.findByUsername(d.getUsername());
    	if (sameUsername != null && !sameUsername.getUsername().equals(old.getUsername()))
    		return new Message("failure","Username Taken");
    	firmRepository.setById(old.getId(), d.getUsername(), d.getPassword(), d.getName(), d.getLocation(),
    			d.getCuisine(), d.getOpen_time(), d.getClose_time(), d.getEmployee_count());
    	return new Message("success");
    }
    
    /**
     * Allows an Admin of any type to remove a Firm from the Firm repository.
     * This method will always return a message, either a success following correct information
     * being given, or a failure with an error explaining why. Errors include wrong username, wrong password,
     * and no such firm in repository.
     * @param body a RemoveEntity class
     * @return  a Message with status of removal (success/failure with error)
     * @see RemoveEntity
     */
    @PostMapping("/admins-remove-firm")
    public Message removeFirm(@RequestBody RemoveEntity body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	Firm firm = firmRepository.findById(body.getId());
    	if (firm == null)
    		return new Message("failure","No Such User");
    	firmRepository.deleteById(firm.getId());
    	List<Category> categories = categoryRepository.findByFirmId(firm.getId());
    	for (Category c : categories) categoryRepository.deleteById(c.getId());
    	List<Item> items = itemRepository.findByFirmId(firm.getId());
    	for (Item i : items) itemRepository.deleteById(i.getId());
    	List<Order> orders = orderRepository.findByFirmId(firm.getId());
    	for (Order o : orders) {
    		orderRepository.setById(o.getId(), 3);
    		List<OrderItem> orderItems = orderItemRepository.findByOrderId(o.getId());
    		for (OrderItem oi : orderItems) orderItemRepository.deleteById(oi.getId());
    	}
    	return new Message("success");
    }
    
}
