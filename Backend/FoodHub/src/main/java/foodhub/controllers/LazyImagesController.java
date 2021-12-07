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
	
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired private CategoryImageRepository categoryImageRepository;
	
	@PostMapping("/upload-category-image")
	public Message uploadCategoryImage(@RequestBody ImageInput body) {
		System.out.println("AA");
    	Firm firm = firmRepository.findByUsername(body.getUsername());
		System.out.println("BB");
		System.out.println(body.getUsername());
    	if (firm == null)
    		return new Message("failure","Wrong Username");
		System.out.println("CC");
    	if (!firm.getPassword().equals(body.getPassword()))
        	return new Message("failure","Wrong Password");
		System.out.println("EEE");
    	String data = body.getData();
    	if (data == null)
    		return new Message("failure","No Data");
		System.out.println("FFF");
    	Category category = categoryRepository.findById(body.getId());
    	if (category == null)
    		return new Message("failure","No Such Category");
		System.out.println("AAAAAA");
    	CategoryImage image = new CategoryImage("name", "string", body.getId(), data.getBytes());
		System.out.println("BBBBBB");
    	categoryImageRepository.save(image);
		System.out.println("CCCCCC");
		return new Message("success");
	}
	
	@PostMapping("/download-category-image")
	public MessageAndString uploadCategoryImage(@RequestBody Id body) {
		CategoryImage image = categoryImageRepository.findById(body.getId());
		if (image == null)
			return new MessageAndString("failure","No Such Data","");
		return new MessageAndString("success","",new String(image.getData()));
	}

}
