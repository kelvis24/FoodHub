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
	
	// TODO: No methods here require authentication. Please Remove.
	
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

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

    @PostMapping("/general-add-customer")
    public String createCustomer(@RequestBody Customer customer) {
    	if (customer == null)
    		return failure;
    	Customer sameEmail = customerRepository.findByUsername(customer.getUsername());
    	if (sameEmail != null)
    		return failure;
    	customerRepository.save(customer);
    	return success;
    }

    @GetMapping(path = "/general-get-firms")
    public List<FirmInfo> getFirms() {
    	ArrayList<FirmInfo> fi = new ArrayList<FirmInfo>();
    	List<Firm> fl = firmRepository.findAll();
    	Iterator<Firm> it = fl.iterator();
    	while (it.hasNext()) {
    		fi.add(new FirmInfo(it.next()));
    	}
    	return fi;
    }
    
    //List categories+items by firm methods below this point
    
    @PostMapping(path = "/get-firm-categories")
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
    	Firm firm = firmRepository.findByName(body.getFirmName());
    	List<Category> categories = categoryRepository.findByFirmId(firm.getId());
    	return categories.toString();
    }
    
    @PostMapping(path = "/get-firm-items")
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
    	Firm firm = firmRepository.findByName(body.getFirmName());
    	List<Item> items = itemRepository.findByFirmId(firm.getId());
    	return items.toString();
    }
}
