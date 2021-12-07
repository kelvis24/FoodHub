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
 * The controller for all Customer backend methods
 * @author 1_CW_2
 */
@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FirmRepository firmRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OTMessageRepository otmRepository;
	
	/**
	 * Authenticates a Customers login.
	 * This method returns a message, either a success on successful authentication
	 * or a failure with error on failure. Errors include wrong username and wrong password.
	 * @param body an Authentication entity, includes Customer username and password.
	 * @return a Message with status of authentication (success/failure with error)
	 * @see Authentication
	 */
	@PostMapping("/customers-authenticate")
	public Message authenticateCustomer(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	return new Message("success");
	}
	
	@PostMapping("/customers-get-info")
	public NameAndLocation getInfo(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new NameAndLocation();
    	if (!user.getPassword().equals(body.getPassword()))
    		return new NameAndLocation();
    	return new NameAndLocation(user.getName(), user.getLocation());
	}
    
	/**
	 * Allows a Customer to edit their own information. 
	 * This method always returns a Message after being called, either a success after successfully editing 
	 * the customer information or a failure with error on failure. Errors include wrong username, wrong password,
	 * no customer edits made (null Customer), or the new username already taken in the Customer repository. 
	 * @param body an EditCustomerInput, which includes Customer login and new Customer information
	 * @return a Message describing method result (success/failure with error).
	 * @see EditCustomerInput
	 */
    @PostMapping("customers-edit-customer")
    public Message createCustomer(@RequestBody EditCustomerInput body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	CustomerInfo d = body.getData();
    	Customer sameUsername = customerRepository.findByUsername(d.getUsername());
    	if (sameUsername != null && !sameUsername.getUsername().equals(user.getUsername()))
    		return new Message("failure","Username Taken");
    	customerRepository.setById(user.getId(),d.getUsername(),d.getPassword(),d.getName(),user.getLocation());
    	return new Message("success");
    }
    
    /**
     * Allows a Customer to remove themselves from the repository.
     * This method always returns a Message, either stating the success of removal or a failure with 
     * error of why the Customer could not be removed. Errors include wrong username and wrong password. 
     * @param body an Authentication entity, which needs the Customers login details
     * @return a Message stating the result of the method (success/failure with error)
     * @see Authentication
     */
    @PostMapping("customers-remove-customer")
    public Message removeCustomer(@RequestBody Authentication body) {
    	Customer user = customerRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","Wrong Username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	List<Order> orders = orderRepository.findByCustomerId(user.getId());
    	for (Order o : orders) deleteOrder(o.getId());
    	customerRepository.deleteById(user.getId());
    	return new Message("success");
    }
    
    /**
     * Shows a Customer all their current orders.
     * This method always returns a List of OrderOutputs. This list can be null, and will
     * be null if the body parameter is not successfully filled with correct information.
     * @param body an Authentication entity, which includes Customer login details
     * @return a List of OrderOutputs for the authorized Customer. 
     * @see Authentication
     */
    @PostMapping("/customers-get-orders")
    public List<OrderOutput> getOrders(@RequestBody Authentication body) {
    	List<OrderOutput> output = new ArrayList<OrderOutput>();
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null || !customer.getPassword().equals(body.getPassword()))
        	return output;
    	List<Order> orders = orderRepository.findByCustomerId(customer.getId());
    	for (Order order : orders) {
    		if (order.getStatus() == 3) {
	    		output.add(new OrderOutput("removed", customer.getName(), customer.getLocation(), order, new ArrayList<OrderItemOutput>()));
    		}
    		else {
	    		Firm firm = firmRepository.getById(order.getFirmId());
	    		List<OrderItemOutput> orderList = new ArrayList<OrderItemOutput>();
	    		List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
	    		for (OrderItem orderItem : orderItems) {
	    			Item item = itemRepository.findById(orderItem.getItemId());
	    			orderList.add(new OrderItemOutput(orderItem, item));
	    		}
	    		output.add(new OrderOutput(firm.getName(), customer.getName(), customer.getLocation(), order, orderList));
    		}
    	}
    	return output;
    }
    
    /**
     * Allows a Customer to create a new order to a Firm.
     * This method always returns a message, either after successfully creating a new order in the order
     * repository, or a failure with an error explaining why this could not be done. Errors include wrong username,
     * wrong password, no data being given (null), the selected firm not existing in the firm repository (null), or no such
     * item existing within the item repository (null).
     * @param body an AddOrderInput entity, which includes Customer login details and Order details
     * @return a Message detailing the status of the method (success/failure with error)
     * @see AddOrderInput
     */
    @PostMapping("/customers-create-order")
    public Message customerOrders(@RequestBody AddOrderInput body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return new Message("failure","Wrong Username");
    	if (!customer.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	if (body.getData() == null)
    		return new Message("failure","No Data");
    	OrderInfo data = body.getData();
    	Firm firm = firmRepository.findById(data.getFirmId());
    	if (firm == null)
    		return new Message("failure","No Such Firm");
    	Order order = new Order(firm.getId(), customer.getId(), 0);
    	orderRepository.save(order);
    	List<OrderItemInfo> list = data.getOrderList();
    	for (OrderItemInfo o : list) {
    		Item item = itemRepository.findById(o.getItemId());
        	if (item == null) {
        		deleteOrder(order.getId());
        		return new Message("failure","No Such Item");
        	}
        	OrderItem orderItem = new OrderItem(order.getId(), item.getId(), o.getQuantity(), o.getNotes());
        	orderItemRepository.save(orderItem);
    	}
    	return new Message("success");
    }
    
    /**
     * Allows a Customer to remove an Order from the Order repository.
     * This method always returns a Message after invoction, either a success after successful implementation
     * or a failure with error explaining why it failed. Errors include wrong username, wrong password, or the order
     * status being currently pending.
     * @param body a RemoveEntity entity, including Customer login information and Order information
     * @return a Message detailing the status of the method (success/failure with error)
     */
    @PostMapping("customers-remove-order")
    public Message removeOrder(@RequestBody RemoveEntity body) {
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return new Message("failure","Wrong Username");
    	if (!customer.getPassword().equals(body.getPassword()))
    		return new Message("failure","Wrong Password");
    	Order order = orderRepository.findById(body.getId());
    	if (order.getStatus() == 0)
    		return new Message("failure","Not Permitted");
    	deleteOrder(order.getId());
    	return new Message("success");
    }
    
    @PostMapping("customers-get-otmessages")
    public List<OTMessageOutput> getOTMessages(@RequestBody AuthenticationAndId body) {
    	List<OTMessageOutput> output = new ArrayList<>();
    	Customer customer = customerRepository.findByUsername(body.getUsername());
    	if (customer == null)
    		return output;
    	if (!customer.getPassword().equals(body.getPassword()))
    		return output;
    	List<OTMessage> list = otmRepository.findByOrderId(body.getId());
    	for (OTMessage m : list) output.add(new OTMessageOutput(m));
    	return output;
    }
    
    /**
     * Deletes an Order from the repository based on its ID
     * @param id the ID of the order to be deleted
     */
    private void deleteOrder(long id) {
    	orderRepository.deleteById(id);
    	List<OrderItem> list = orderItemRepository.findByOrderId(id);
    	for (OrderItem o : list) orderItemRepository.deleteById(o.getId());;
    	List<OrderItem> oilist = orderItemRepository.findByOrderId(id);
    	for (OrderItem i : oilist) orderItemRepository.deleteById(i.getId());
    	List<OTMessage> otlist = otmRepository.findByOrderId(id);
    	for (OTMessage m : otlist) otmRepository.deleteById(m.getId());

    }
    
    //comment

}
