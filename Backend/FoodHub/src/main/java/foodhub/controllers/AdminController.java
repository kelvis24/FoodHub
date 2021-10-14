package foodhub.controllers;

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
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
    
    @GetMapping(path = "/admins")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
    
    @PostMapping(path = "/admins-create-admin")
    public String createAdmin(@RequestBody AdminInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return failure;
    	Admin admin = body.getData();
    	if (admin == null)
    		return failure;
    	Admin sameUsername = adminRepository.findByUsername(admin.getUsername());
    	if (sameUsername != null)
    		return failure;
    	admin.setType(0);
    	adminRepository.save(admin);
    	return success;
    }
    
    @PostMapping(path = "remove-admin")
    public String removeAdmin(@RequestBody AdminInput body) {
    	Admin owner = adminRepository.findByUsername(body.getUsername());
    	if (owner == null || !owner.getPassword().equals(body.getPassword()) || owner.getType() != 1)
    		return failure;
    	Admin admin = adminRepository.findByUsername(body.getAdminUsername());
    	if (admin == null) {
    		return failure;
    	}
    	adminRepository.deleteById(admin.getId());
    	return success;
    }

    @PostMapping(path = "/admins-create-firm")
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
    
    @PostMapping(path = "/remove-firm")
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
    
    @PostMapping(path = "/get-admins")
    public String getAdmins(@RequestBody LoginInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()) || user.getType() != 1)
    		return failure;
    	List<Admin> admins = adminRepository.findAll();
    	return admins.toString();
    }
    
    @PostMapping(path = "/get-firm-menu")
    public String getFirmMenu(@RequestBody FirmInput body) {
    	Admin user = adminRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()))
    		return failure;
    	Firm firm = firmRepository.findByName(body.getFirmName());
    	List<Item> items = itemRepository.findByFirmId(firm.getId());
    	return items.toString();
    }
}
