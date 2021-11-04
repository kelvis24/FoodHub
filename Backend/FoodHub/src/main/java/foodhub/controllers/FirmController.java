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
 * The controller for all Firm backend methods.
 * All permissions that a Firm has are held within this class.
 * @author 1_CW_2
 *
 */
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
	
	/**
	 * Authenticates a Firm using its login credentials.
	 * This method always returns a Message, either stating the success of authorization
	 * or a failure with an error explaining why it failed. Errors include wrong username
	 * and wrong password.
	 * @param body an Authentication entity, with Firm login details
	 * @return a Message describing method status (success/failure with error)
	 * @see Authentication
	 */
	@PostMapping("/firms-authenticate")
	public MessageAndId authenticateFirm(@RequestBody Authentication body) {
    	Firm user = firmRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new MessageAndId("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new MessageAndId("failure","wrong password");
    	return new MessageAndId("success", user.getId());
	}

	/**
	 * Allows a Firm to create a Category to add to their menu.
	 * This method always returns a Message describing the status of itself, either a success
	 * following a successful addition of a category, or a failure with an error explaining why 
	 * it failed. Errors include wrong username, wrong password, or no category data given (null).
	 * @param body an AddCategoryInput, which includes Firm login details and Category details
	 * @return a Message describing the status of the method (success/failure with error)
	 * @see AddCategoryInput
	 */
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
    
    /**
     * Allows a Firm to edit the information related to a Category on its menu.
     * This method always returns a message about the status of iteslf, either a success
     * on successful running or a failure with an error explaining why it failed. Errors include
     * wrong username, wrong password, the new category information not being supplied (null), or the
     * category selected to be edited not existing. 
     * @param body an EditCategoryInput entity, which includes Firm login details and the Category selected to be edited.
     * @return a Message detailing the status of the method (success/failure with error)
     * @see EditCategoryInput
     */
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
    
    /**
     * Allows a Firm to remove a Category from its menu.
     * This method always returns a message about its own status, either a success on successfully removing
     * the category, or a failure with an error explaining why the method failed. Errors include wrong
     * username, wrong password, or the selected category not existing (null).
     * @param body a RemoveEntity entity, which includes Firm login details and the Category to be deleted
     * @return a Message describing the status of the removal (success/failure with error)
     * @see RemoveEntity
     */
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

	/**
	 * Allows a Firm to create an Item to add to their menu.
	 * This method always returns a Message describing the status of itself, either a success
	 * following a successful addition of a item, or a failure with an error explaining why 
	 * it failed. Errors include wrong username, wrong password, no item data given (null), or 
	 * the specified category not existing (null).
	 * @param body an AddItemInput, which includes Firm login details and Item details.
	 * @return a Message describing the status of the method (success/failure with error)
	 * @see AddItemInput
	 */
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
    
    /**
     * Allows a Firm to edit the information related to a Item on its menu.
     * This method always returns a message about the status of itself, either a success
     * on successful running or a failure with an error explaining why it failed. Errors include
     * wrong username, wrong password, the new Item information not being supplied (null), or the
     * Item selected to be edited not existing. 
     * @param body an EditItemInput entity, which includes Firm login details and the Item selected to be edited.
     * @return a Message detailing the status of the method (success/failure with error)
     * @see EditItemInput
     */
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
    
    /**
     * Allows a Firm to remove an Item from its menu.
     * This method always returns a message about its own status, either a success on successfully removing
     * the item, or a failure with an error explaining why the method failed. Errors include wrong
     * username, wrong password, or the selected item not existing (null).
     * @param body a RemoveEntity entity, which includes Firm login details and the Item to be deleted
     * @return a Message describing the status of the removal (success/failure with error)
     * @see RemoveEntity
     */
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
    
    /**
     * Lets Firms see all the incoming Orders from Customers.
     * This method always returns a List, including ones that may be empty.
     * Empty order lists are given in the case of a wrong username, wrong password, or
     * no orders currently being made to the Firm.
     * @param body an Authentication entity for login information for a Firm
     * @return a List of all orders connected to the authenticated firm.
     * @see Authentication
     */
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
    
    /**
     * Allows a Firm to set the status of an Order as complete
     * @param body a CompleteOrderInput, which includes Firm login details and order details
     * @return a Message stating the status of completion (success/failure with error)
     * @see CompleteOrderInput
     */
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
