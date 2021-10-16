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
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

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
    
    @PostMapping(path = "/general-get-categories")
    public String getCategories(@RequestBody LoginInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return failure;
    	List<Category> categories = categoryRepository.findAll();
    	return categoryRepository.findByFirmId(1);
    }
    
    @PostMapping(path = "/general-get-items")
    public String getItems(@RequestBody LoginInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return failure;
    	List<Item> items = itemRepository.findAll();
    	return items.toString();
    }
}
