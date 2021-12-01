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
import foodhub.database.FirmImage;
import foodhub.database.ItemImage;
import foodhub.ioObjects.Authentication;


@Controller
public class ImagesController {

	@Autowired 
	private ImageStorageService imageStorageService;
	
	@PostMapping("/uploadFirmFiles")
	public String uploadMultipleFilesFirm(@RequestParam("files") MultipartFile[] files, Authentication login) {
		for (MultipartFile file: files) {
			imageStorageService.saveFirmFile(file, login);
			
		}
		return "redirect:/";
	}
	
	@PostMapping("/uploadCategoryFiles")
	public String uploadMultipleFilesCategory(@RequestParam("files") MultipartFile[] files, Authentication login, long categoryID) {
		for (MultipartFile file: files) {
			imageStorageService.saveCategoryFile(file, login, categoryID);
			
		}
		return "redirect:/";
	}
	
	@PostMapping("/uploadFirmFiles")
	public String uploadMultipleFilesItem(@RequestParam("files") MultipartFile[] files, Authentication login, long itemID) {
		for (MultipartFile file: files) {
			imageStorageService.saveItemFile(file, login, itemID);
			
		}
		return "redirect:/";
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