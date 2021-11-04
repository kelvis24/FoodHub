package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

/**
 * General commands that all users can operate/use.
 * @author 1_CW_2
 *
 */
@RestController
public class GeneralController {
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;

	/**
	 * Lists all Firms within the Firm repository.
	 * @return a List of FirmOutput. Can be empty.
	 * @see FirmOutput
	 */
    @GetMapping("/general-get-firms")
    public List<FirmOutput> getFirms() {
    	List<FirmOutput> output = new ArrayList<FirmOutput>();
    	List<Firm> firms = firmRepository.findAll();
    	for (Firm f : firms) output.add(new FirmOutput(f));
    	return output;
    }
    
    /**
     * Lists all Categories associated with a specific Firm
     * @param body the ID of the specified Firm.
     * @return a List of CategoryOutput
     * @see CategoryOutput
     */
    @PostMapping("/general-get-categories")
    public List<CategoryOutput> getCategories(@RequestBody Id body) {
    	List<CategoryOutput> output = new ArrayList<CategoryOutput>();
    	List<Category> categories = categoryRepository.findByFirmId(body.getId());
    	for (Category c : categories) output.add(new CategoryOutput(c));
    	return output;
    }
    
    /**
     * Lists all Items associated with a specific Category
     * @param body the ID of the specified Category
     * @return a List of ItemOutputs
     * @see ItemOutput
     */
    @PostMapping("/general-get-items")
    public List<ItemOutput> getItems(@RequestBody Id body) {
    	List<ItemOutput> output = new ArrayList<ItemOutput>();
    	List<Item> items = itemRepository.findByCategoryId(body.getId());
    	for (Item i : items) output.add(new ItemOutput(i));
    	return output;
    }
    
    /**
     * Creates a new Customer in the Customer repository.
     * This method can fail, and will do so if the customer is not given data (null) or the
     * selected username is already taken. Upon failure, user will get a Message stating failure
     * with error reason, while success will return success.
     * @param customer a CustomerInfo entity, with the information needed for a Customer account
     * @return a Message stating the status of the creation(success/failure with error)
     */
    @PostMapping("/general-create-customer")
    public Message createCustomer(@RequestBody CustomerInfo customer) {
    	if (customer == null)
    		return new Message("failure","no data");
    	Customer sameEmail = customerRepository.findByUsername(customer.getUsername());
    	if (sameEmail != null)
    		return new Message("failure","username taken");
    	customerRepository.save(new Customer(customer));
    	return new Message("success");
    }
}
