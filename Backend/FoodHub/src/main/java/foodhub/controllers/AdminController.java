package foodhub.controllers;

import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

@RestController
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
    
    @GetMapping(path = "/admins")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
    
    @PostMapping(path = "/admins")
    public String createAdmin(@RequestBody AdminInput body) {
    	boolean fail = true;
    	Admin current;
    	List<Admin> owners = adminRepository.findByType(1);
    	Iterator<Admin> it = owners.iterator();
    	while (it.hasNext()) {
    		current = it.next();
    		if (current.getEmail().equals(body.getUsername())) {
    			if (current.getPassword().equals(body.getPassword())) {
    				fail = false;
    				break;
    			}
    			else {
    				return failure;
    			}
    		}
    	}
    	if (fail)
    		return failure;
    	Admin admin = body.getData();
    	if (admin == null)
    		return failure;
    	List<Admin> sameEmail = adminRepository.findByEmail(admin.getEmail());
    	if (sameEmail != null  && 0 < sameEmail.size())
    		return failure;
    	admin.setType(0);
    	adminRepository.save(admin);
    	return success;
    }

}
