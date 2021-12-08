package foodhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;
import foodhub.ioObjects.*;

// comment

@RestController
public class LazyImagesController {
	
	@Autowired private FirmRepository firmRepository;
	
	@Autowired private FirmImageRepository firmImageRepository;
	
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired private CategoryImageRepository categoryImageRepository;
	
	@Autowired private ItemRepository itemRepository;
	
	@Autowired private ItemImageRepository itemImageRepository;
	
	@PostMapping("/upload-firm-image")
	public Message uploadFirmImage(@RequestBody ImageInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
		if (firm == null)
    		return new Message("failure","Wrong Username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","Wrong Password");
    	String data = body.getData();
    	if (data == null)
    		return new Message("failure","No Data");
    	FirmImage image = new FirmImage("name", "string", data.getBytes(), body.getId());
    	firmImageRepository.save(image);
		return new Message("success");
	}
	
	@PostMapping("/download-firm-image")
	public MessageAndString uploadFirmImage(@RequestBody Id body) {
		FirmImage image = firmImageRepository.findByFirmId(body.getId());
		if (image == null)
			return new MessageAndString("failure","No Such Data","");
		return new MessageAndString("success","",new String(image.getData()));
	}
	
	@PostMapping("/upload-category-image")
	public Message uploadCategoryImage(@RequestBody ImageInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
		if (firm == null)
    		return new Message("failure","Wrong Username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","Wrong Password");
    	String data = body.getData();
    	if (data == null)
    		return new Message("failure","No Data");
    	Category category = categoryRepository.findById(body.getId());
    	if (category == null)
    		return new Message("failure","No Such Category");
    	CategoryImage image = new CategoryImage("name", "string", body.getId(), data.getBytes());
    	categoryImageRepository.save(image);
		return new Message("success");
	}
	
	@PostMapping("/download-category-image")
	public MessageAndString uploadCategoryImage(@RequestBody Id body) {
		CategoryImage image = categoryImageRepository.findByCategoryId(body.getId());
		if (image == null)
			return new MessageAndString("failure","No Such Data","");
		return new MessageAndString("success","",new String(image.getData()));
	}
	
	@PostMapping("/upload-item-image")
	public Message uploadItemImage(@RequestBody ImageInput body) {
    	Firm firm = firmRepository.findByUsername(body.getUsername());
		if (firm == null)
    		return new Message("failure","Wrong Username");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","Wrong Password");
    	String data = body.getData();
    	if (data == null)
    		return new Message("failure","No Data");
    	Item item = itemRepository.findById(body.getId());
    	if (item == null)
    		return new Message("failure","No Such Item");
    	ItemImage image = new ItemImage("name", "string", body.getId(), data.getBytes());
    	itemImageRepository.save(image);
		return new Message("success");
	}
	
	@PostMapping("/download-item-image")
	public MessageAndString uploadItemImage(@RequestBody Id body) {
		ItemImage image = itemImageRepository.findByItemId(body.getId());
		if (image == null)
			return new MessageAndString("failure","No Such Data","");
		return new MessageAndString("success","",new String(image.getData()));
	}

}
