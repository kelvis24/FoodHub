package foodhub.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import foodhub.ImageStorageService;
import foodhub.database.CategoryImage;
import foodhub.database.Firm;
import foodhub.database.FirmImage;
import foodhub.database.Item;
import foodhub.database.ItemImage;
import foodhub.ioObjects.Authentication;
import foodhub.ioObjects.Message;

import foodhub.database.FirmRepository;


@Controller
public class ImagesController {

	@Autowired 
	private ImageStorageService imageStorageService;
	
	@Autowired
	private FirmRepository firmRepository;
	
	@PostMapping("/uploadFirmFiles")
	public Message uploadMultipleFilesFirm(@RequestParam("files") MultipartFile[] files, Authentication login) {
    	Firm firm = firmRepository.findByUsername(login.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(login.getPassword()))
        	return new Message("failure","wrong password");
		for (MultipartFile file: files) {
			imageStorageService.saveFirmFile(file, login);
			
		}
		return new Message("success");
	}
	
	@PostMapping("/uploadCategoryFiles")
	public Message uploadMultipleFilesCategory(@RequestParam("files") MultipartFile[] files, Authentication login, long categoryID) {
    	Firm firm = firmRepository.findByUsername(login.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(login.getPassword()))
        	return new Message("failure","wrong password");
		for (MultipartFile file: files) {
			imageStorageService.saveCategoryFile(file, login, categoryID);
			
		}
		return new Message("success");
	}
	
	@PostMapping("/uploadItemFiles")
	public Message uploadMultipleFilesItem(@RequestParam("files") MultipartFile[] files, Authentication login, long itemID) {
    	Firm firm = firmRepository.findByUsername(login.getUsername());
    	if (firm == null)
    		return new Message("failure","wrong username");
    	if (!firm.getPassword().equals(login.getPassword()))
        	return new Message("failure","wrong password");
		for (MultipartFile file: files) {
			imageStorageService.saveItemFile(file, login, itemID);
			
		}
		return new Message("success");
	}
	
	@GetMapping("/downloadFirmFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFirmFile(@PathVariable Long fileId){
		FirmImage image = imageStorageService.getFirmFileByID(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+image.getImageName()+"\"")
				.body(new ByteArrayResource(image.getData()));
	}
	
	@GetMapping("/downloadCategoryFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadCategoryFile(@PathVariable Long fileId){
		CategoryImage image = imageStorageService.getCategoryFileByID(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+image.getImageName()+"\"")
				.body(new ByteArrayResource(image.getData()));
	}
	
	@GetMapping("/downloadItemFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadItemFile(@PathVariable Long fileId){
		ItemImage image = imageStorageService.getItemFileByID(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(image.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+image.getImageName()+"\"")
				.body(new ByteArrayResource(image.getData()));
	}
}