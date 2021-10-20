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
public class GeneralController {
	
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

    @GetMapping("/general-get-firms")
    public List<FirmOutput> getFirms() {
    	List<FirmOutput> output = new ArrayList<FirmOutput>();
    	List<Firm> firms = firmRepository.findAll();
    	Iterator<Firm> it = firms.iterator();
    	while (it.hasNext()) {output.add(new FirmOutput(it.next()));}
    	return output;
    }
    
    @PostMapping("/general-get-categories")
    public List<CategoryInfo> getFirmCategories(@RequestBody Username body) {
    	List<CategoryInfo> output = new ArrayList<CategoryInfo>();
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return output;
    	List<Category> categories = categoryRepository.findByFirmId(firm.getId());
    	Iterator<Category> it = categories.iterator();
    	while (it.hasNext()) {output.add(new CategoryInfo(it.next()));}
    	return output;
    }
    
    @PostMapping("/general-get-items")
    public List<ItemInfo> getFirmItems(@RequestBody FirmCategory body) {
    	List<ItemInfo> output = new ArrayList<ItemInfo>();
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null)
    		return output;
    	List<Category> categories = categoryRepository.findByFirmId(firm.getId());
    	Category category = (Category)Entitled.findByTitle(categories, body.getTitle());
    	if (category == null)
    		return output;
    	List<Item> items = itemRepository.findByCategoryId(category.getId());
    	Iterator<Item> it = items.iterator();
    	while (it.hasNext()) {output.add(new ItemInfo(it.next()));}
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
