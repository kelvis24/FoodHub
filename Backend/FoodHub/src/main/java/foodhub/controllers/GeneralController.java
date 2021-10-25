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

    @GetMapping("/general-get-firms")
    public List<FirmOutput> getFirms() {
    	List<FirmOutput> output = new ArrayList<FirmOutput>();
    	List<Firm> firms = firmRepository.findAll();
    	for (Firm f : firms) output.add(new FirmOutput(f));
    	return output;
    }
    
    @PostMapping("/general-get-categories")
    public List<CategoryOutput> getCategories(@RequestBody Id body) {
    	List<CategoryOutput> output = new ArrayList<CategoryOutput>();
    	List<Category> categories = categoryRepository.findByFirmId(body.getId());
    	for (Category c : categories) output.add(new CategoryOutput(c));
    	return output;
    }
    
    @PostMapping("/general-get-items")
    public List<ItemOutput> getItems(@RequestBody Id body) {
    	List<ItemOutput> output = new ArrayList<ItemOutput>();
    	List<Item> items = itemRepository.findByCategoryId(body.getId());
    	for (Item i : items) output.add(new ItemOutput(i));
    	return output;
    }
    
    @PostMapping("/general-add-customer")
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
