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
    
    //List categories+items by firm methods below this point
    
    @PostMapping(path = "/get-get-categories")
    public String getFirmCategories(@RequestBody FirmInput body) {
    	int goodUser = 0;
    	if (!(adminRepository.findByUsername(body.getUsername()) == null)) {
    		Admin user = adminRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 1;
    
    	}else if (!(firmRepository.findByUsername(body.getUsername()) == null)) {
    		Firm user = firmRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 2;
    	}else if (!(customerRepository.findByUsername(body.getUsername()) == null)) {
    		Customer user = customerRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 3;
    	}
    	//username not found in any database
    	if (goodUser == 0) {
    		return failure;
    	}
    	Firm firm = firmRepository.findByUsername(body.getData().getUsername());
    	List<Category> categories = categoryRepository.findByFirmId(firm.getId());
    	return categories.toString();
    }
    
    @PostMapping(path = "/general-get-items")
    public String getFirmItems(@RequestBody FirmInput body) {
    	int goodUser = 0;
    	if (!(adminRepository.findByUsername(body.getUsername()) == null)) {
    		Admin user = adminRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 1;
    	}else if (!(firmRepository.findByUsername(body.getUsername()) == null)) {
    		Firm user = firmRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 2;
    	}else if (!(customerRepository.findByUsername(body.getUsername()) == null)) {
    		Customer user = customerRepository.findByUsername(body.getUsername());
        	if (user == null || !user.getPassword().equals(body.getPassword()))
        		return failure;
    		goodUser = 3;
    	}
    	//username not found in any database
    	if (goodUser == 0) {
    		return failure;
    	}
    	Firm firm = firmRepository.findByUsername(body.getData().getUsername());
    	List<Item> items = itemRepository.findByFirmId(firm.getId());
    	return items.toString();
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
