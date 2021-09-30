package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;

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
    
    // Obviously, this is a massive security problem.
    @PostMapping(path = "/admins")
    public String createAdmin(@RequestBody Admin admin) {
    	if (admin == null)
    		return failure;
    	List<Admin> sameEmail = adminRepository.findByEmail(admin.getEmail());
    	if (sameEmail != null  && 0 < sameEmail.size())
    		return failure;
    	adminRepository.save(admin);
    	return success;
    }

}
