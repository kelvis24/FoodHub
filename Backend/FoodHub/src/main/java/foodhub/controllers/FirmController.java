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
import foodhub.ioObjects.ItemInput;

@RestController
public class FirmController {
	
	@Autowired
	FirmRepository firmRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ItemRepository itemRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";
	private String errorUser = "{\"message\":\"user not defined/found\"}";

    @GetMapping("/firms")
    public List<Firm> listFirms(Model model) {
    	return firmRepository.findAll();
    }
    
    //TO-DO: Fix error that does not allow multiple firms to have the same Title
    @PostMapping(path = "create-category")
    public String createCategory(@RequestBody CategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = body.getCategory();
    	if (category == null)
    		return failure;
    	category.setFirmId(firm.getId());
    	categoryRepository.save(category);
    	return success;
    }
    
    @PostMapping(path = "remove-category")
    public String removeCateogry(@RequestBody CategoryInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategoryTitle());
    	if (category == null) {
    		return failure;
    	}
    	categoryRepository.deleteById(category.getId());
    	return success;
    }
    
    //Work on
    @PostMapping(path = "/categories")
    public List<Category> listCategories(Model model) {
    	return categoryRepository.findAll();
    }

    //Add item
    @PostMapping(path = "/create-item")
    public String createItem(@RequestBody ItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategory());
    	if (category == null) {
    		return failure;
    	}
    	Item item = body.getItem();
    	if (item == null) {
    		return failure;
    	}
    	item.setFirmId(firm.getId());
    	item.setCategoryId(category.getId());
    	itemRepository.save(item);
    	return success;
    }
    
    @PostMapping(path = "remove-item")
    public String removeItem(@RequestBody ItemInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
    	if (firm == null || !firm.getPassword().equals(body.getPassword()))
    		return errorUser;
    	Category category = categoryRepository.findByTitle(body.getCategory());
    	if (category == null) {
    		return failure;
    	}
    	Item item = itemRepository.findByTitle(body.getItemTitle());
    	if (item == null) {
    		return failure;
    	}
    	itemRepository.deleteById(item.getId());
    	return success;
    }
    
    //get orders
    //edit category
    //edit item
}
