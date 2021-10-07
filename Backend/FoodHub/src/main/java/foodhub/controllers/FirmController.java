package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.CategoryInput;
import foodhub.ioObjects.FirmInput;

@RestController
public class FirmController {
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/firms")
    public List<Firm> listFirms(Model model) {
    	return firmRepository.findAll();
    }
    
    @PostMapping(path = "/firms-create-categories")
    public String createFirm(@RequestBody CategoryInput body) {
    	Firm user = firmRepository.findByUsername(body.getUsername());
    	if (user == null || !user.getPassword().equals(body.getPassword()))
    		return failure;
    	Category category = body.getCategory();
    	if (category == null)
    		return failure;
    	Category sameTitle = categoryRepository.findByTitle(category.getTitle());
    	if (sameTitle != null)
    		return failure;
    	user.addCategory(category);
    	firmRepository.save(user);
    	return success;
    }
    
    //Test method, do not use
    @PostMapping(path = "firms-list-categories")
    public String listCategories(@RequestBody Firm firm) {
    	return firm.getCategories().toString();
    }
    
}
