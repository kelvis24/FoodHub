package foodhub.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

@RestController
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	FirmRepository firmRepository;
	
	@PostMapping("/admins-authenticate")
	public Message authenticateAdmin(@RequestBody Authentication body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (user.getType() != 1)
    		return new Message("failure","wrong credentials");
    	return new Message(user.getType() == 0 ? "admin" : "owner");
	}
    
    @PostMapping("/admins-get-admins")
    public List<AdminOutput> getAdmins(@RequestBody Authentication body) {
    	List<AdminOutput> output = new ArrayList<AdminOutput>();
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return output;
    	List<Admin> admins = adminRepository.findAll();
    	for (Admin a : admins) {output.add(new AdminOutput(a));}
    	return output;
    }
    
    @PostMapping("/admins-create-admin")
    public Message createAdmin(@RequestBody AddAdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (user.getType() != 1)
    		return new Message("failure","wrong credentials");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Admin admin = new Admin(body.getData());
    	Admin sameUsername = adminRepository.findByUsername(admin.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","username taken");
    	adminRepository.save(admin);
    	return new Message("success");
    }
    
    @PostMapping("/admins-edit-admin")
    public Message editAdmin(@RequestBody EditAdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (user.getType() != 1)
    		return new Message("failure","wrong credentials");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	AdminInfo d = body.getData();
    	Admin sameUsername = adminRepository.findByUsername(d.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","username taken");
    	Admin old = adminRepository.findByUsername(body.getSubject());
    	if (old == null)
    		return new Message("failure","no such user");
    	adminRepository.setById(old.getId(),d.getUsername(),d.getPassword(),d.getName(),old.getType());
    	return new Message("success");
    }
    
    @PostMapping("admins-remove-admin")
    public Message removeAdmin(@RequestBody RemoveUserInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (user.getType() != 1)
    		return new Message("failure","wrong credentials");
    	if (body.getUser() == null)
    		return new Message("failure","no data");
    	Admin admin = adminRepository.findByUsername(body.getUser());
    	if (admin == null)
    		return new Message("failure","no such user");
    	if (admin.getType() == 1)
    		return new Message("failure","not permitted");
    	adminRepository.deleteById(admin.getId());
    	return new Message("success");
    }

    @PostMapping("/admins-create-firm")
    public Message createFirm(@RequestBody AddFirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	Firm firm = new Firm(body.getData());
    	Firm sameUsername = firmRepository.findByUsername(firm.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","username taken");
    	firmRepository.save(firm);
    	return new Message("success");
    }
    
    @PostMapping("/admins-edit-firm")
    public Message editFirm(@RequestBody EditFirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (body.getData() == null)
    		return new Message("failure","no data");
    	FirmInfo d = body.getData();
    	Firm sameUsername = firmRepository.findByUsername(d.getUsername());
    	if (sameUsername != null)
    		return new Message("failure","username taken");
    	Firm old = firmRepository.findByUsername(body.getSubject());
    	if (old == null)
    		return new Message("failure","no such user");
    	firmRepository.setById(old.getId(), d.getUsername(), d.getPassword(), d.getName(), d.getLocation(),
    			d.getCuisine(), d.getOpen_time(), d.getClose_time(), d.getEmployee_count());
    	return new Message("success");
    }
    
    // TODO: deal with cascading effects of deleting firms
    
    @PostMapping("/admins-remove-firm")
    public Message removeFirm(@RequestBody RemoveUserInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("failure","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("failure","wrong password");
    	if (body.getUser() == null)
    		return new Message("failure","no data");
    	Firm firm = firmRepository.findByUsername(body.getUser());
    	if (firm == null)
    		return new Message("failure","no such user");
    	firmRepository.deleteById(firm.getId());
    	return new Message("success");
    }
    
}
