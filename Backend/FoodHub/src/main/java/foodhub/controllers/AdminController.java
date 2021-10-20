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
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	FirmRepository firmRepository;
    
    @PostMapping(path = "/admins-get-admins")
    public List<AdminOutput> getAdmins(@RequestBody Authentication body) {
    	List<AdminOutput> output = new ArrayList<AdminOutput>();
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return output;
    	List<Admin> admins = adminRepository.findAll();
    	Iterator<Admin> it = admins.iterator();
    	while (it.hasNext()) {output.add(new AdminOutput(it.next()));}
    	return output;
    }
    
    @PostMapping("/admins-create-admin")
    public Message createAdmin(@RequestBody AdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("error","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("error","wrong password");
    	if (user.getType() != 1)
    		return new Message("error","wrong credentials");
    	if (body.getData() == null)
    		return new Message("error","no data");
    	Admin admin = new Admin(body.getData());
    	Admin sameUsername = adminRepository.findByUsername(admin.getUsername());
    	if (sameUsername != null)
    		return new Message("error","username taken");
    	adminRepository.save(admin);
    	return new Message("success");
    }
    
    @PostMapping("/admins-edit-admin")
    public Message editAdmin(@RequestBody AdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("error","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("error","wrong password");
    	if (user.getType() != 1)
    		return new Message("error","wrong credentials");
    	if (body.getData() == null)
    		return new Message("error","no data");
    	Admin novel = new Admin(body.getData());
    	Admin old = adminRepository.findByUsername(novel.getUsername());
    	if (old == null)
    		return new Message("error","no such user");
    	novel.setType(old.getType());
    	adminRepository.deleteById(old.getId());
    	adminRepository.save(novel);
    	return new Message("success");
    }
    
    @PostMapping("admins-remove-admin")
    public Message removeAdmin(@RequestBody RemoveUserInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null)
    		return new Message("error","wrong username");
    	if (!user.getPassword().equals(body.getPassword()))
    		return new Message("error","wrong password");
    	if (user.getType() != 1)
    		return new Message("error","wrong credentials");
    	if (body.getUser() == null)
    		return new Message("error","no data");
    	Admin admin = adminRepository.findByUsername(body.getUser());
    	if (admin == null)
    		return new Message("error","no such user");
    	adminRepository.deleteById(admin.getId());
    	return new Message("success");
    }

    @PostMapping("/admins-get-firms")
    public String getFirms(@RequestBody Authentication body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return failure;
    	List<Firm> firms = firmRepository.findAll();
    	return firms.toString();
    }

    @PostMapping("/admins-create-firm")
    public String createFirm(@RequestBody FirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()))
    		return failure;
    	Firm firm = body.getData();
    	if (firm == null)
    		return failure;
    	Firm sameUsername = firmRepository.findByUsername(firm.getUsername());
    	if (sameUsername != null)
    		return failure;
    	firmRepository.save(firm);
    	return success;
    }
    
    @PostMapping("/admins-remove-firm")
    public String removeFirm(@RequestBody FirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()))
    		return failure;
    	Firm firm = firmRepository.findByName(body.getFirmName());
    	if (firm == null)
    		return failure;
    	firmRepository.deleteById(firm.getId());
    	return success;
    }
    
    @PostMapping("/admind-edit-firm")
    public String editFirm(@RequestBody FirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()))
    		return failure;
    	Firm firm = firmRepository.findByName(body.getFirmName());
    	if (firm == null) {
    		return failure;
    	}
    	firm.setOpen_time(body.getData().getOpen_time());
    	firm.setClose_time(body.getData().getClose_time());
    	firm.setCuisine(body.getData().getCuisine());
    	firm.setEmployee_count(body.getData().getEmployee_count());
    	firm.setLocation(body.getData().getLocation());
    	firm.setName(body.getData().getName());
    	return success;
    }
}
