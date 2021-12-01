package foodhub;

import java.util.ArrayList;
import java.util.List;
import foodhub.database.FirmRepository;
import foodhub.database.ItemImage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import foodhub.database.Admin;
import foodhub.database.CategoryImage;
import foodhub.database.CategoryImageRepository;
import foodhub.database.Firm;
import foodhub.database.FirmImage;
import foodhub.database.FirmImageRepository;
import foodhub.database.ItemImageRepository;
import foodhub.ioObjects.AdminOutput;
import foodhub.ioObjects.Authentication;
import foodhub.ioObjects.MessageAndId;

@Service
public class ImageStorageService {
	
	@Autowired
	private FirmImageRepository firmImageRepo;
	
	@Autowired
	private FirmRepository firmRepository;
	
	@Autowired
	private CategoryImageRepository categoryImageRepo;
	
	@Autowired
	private ItemImageRepository itemImageRepo;
	
	/**
	 * Method for saving an image for use in the firm headers.
	 * @param file The image we are saving to the Firm Images repository
	 * @param login The login credentials of the firm uploading the image
	 * @return ??
	 */
	  public FirmImage saveFirmFile(MultipartFile file, Authentication login) {
		  Firm user = firmRepository.findByUsername(login.getUsername());
		  if (user == null)
			  return null; //todo, replace me with something else
		  if (!user.getPassword().equals(login.getPassword()))
			  return null; //todo, replace me with something else
		  String imageName = file.getOriginalFilename();
		  try {
			  FirmImage image = new FirmImage(imageName,file.getContentType(),file.getBytes(), user.getId());
			  return firmImageRepo.save(image);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
	  
	  /**
	   * Return an image based on ITS ID
	   * @param fileId the ID of the image we want to download
	   * @return the image at that id
	   */
	  public Optional<FirmImage> getFirmFileByID(Long fileId) {
		  return firmImageRepo.findById(fileId);
	  }
	  
	  /**
	   * Return a list of all firm images for a firm
	   * @param firmID The ID of the firm we want all the images from
	   * @return
	   */
	  public List<FirmImage> getFirmFiles(long firmID){
		  List<FirmImage> firmImages = new ArrayList<FirmImage>();
		  List<FirmImage> allFirmImage = firmImageRepo.findAll();
		  for (FirmImage f : allFirmImage) {
			  if (f.getFirmId() == firmID) {
				  firmImages.add(f);
			  }
		  }
		  return firmImages;
	  }
	  
	  
	  //CATEGORY
		/**
		 * Method for saving an image for use in the category headers.
		 * @param file The image we are saving to the Category Images repository
		 * @param login The login credentials of the firm uploading the image
		 * @param the ID of the category we are uploading to
		 * @return ??
		 */
		  public CategoryImage saveCategoryFile(MultipartFile file, Authentication login, Long CategoryID) {
			  Firm user = firmRepository.findByUsername(login.getUsername());
			  if (user == null)
				  return null; //todo, replace me with something else
			  if (!user.getPassword().equals(login.getPassword()))
				  return null; //todo, replace me with something else
			  String imageName = file.getOriginalFilename();
			  try {
				  CategoryImage image = new CategoryImage(imageName,file.getContentType(),CategoryID, file.getBytes());
				  return categoryImageRepo.save(image);
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			  return null;
		  }
		  
		  /**
		   * Return an image based on ITS ID
		   * @param fileId the ID of the image we want to download
		   * @return the image at that id
		   */
		  public Optional<CategoryImage> getCategoryFileByID(Long fileId) {
			  return categoryImageRepo.findById(fileId);
		  }
		  
		  /**
		   * Return a list of all category images for a category
		   * @param categoryID The ID of the category we want all the images from
		   * @return
		   */
		  public List<CategoryImage> getCategoryFiles(long categoryID){
			  List<CategoryImage> categoryImages = new ArrayList<CategoryImage>();
			  List<CategoryImage> allCategoryImage = categoryImageRepo.findAll();
			  for (CategoryImage c : allCategoryImage) {
				  if (c.getCategoryId() == categoryID) {
					  categoryImages.add(c);
				  }
			  }
			  return categoryImages;
		  }
		  
		  
		  //ITEMS
		  
			/**
			 * Method for saving an image for use in the firm headers.
			 * @param file The image we are saving to the Firm Images repository
			 * @param login The login credentials of the firm uploading the image
			 * @return ??
			 */
			  public ItemImage saveItemFile(MultipartFile file, Authentication login, Long itemID) {
				  Firm user = firmRepository.findByUsername(login.getUsername());
				  if (user == null)
					  return null; //todo, replace me with something else
				  if (!user.getPassword().equals(login.getPassword()))
					  return null; //todo, replace me with something else
				  String imageName = file.getOriginalFilename();
				  try {
					  ItemImage image = new ItemImage(imageName,file.getContentType(),itemID, file.getBytes());
					  return itemImageRepo.save(image);
				  }
				  catch(Exception e) {
					  e.printStackTrace();
				  }
				  return null;
			  }
			  
			  /**
			   * Return an image based on ITS ID
			   * @param fileId the ID of the image we want to download
			   * @return the image at that id
			   */
			  public Optional<ItemImage> getItemFileByID(Long fileId) {
				  return itemImageRepo.findById(fileId);
			  }
			  
			  /**
			   * Return a list of all item images for an item
			   * @param firmID The ID of the item we want all the images from
			   * @return
			   */
			  public List<ItemImage> getItemFiles(long itemID){
				  List<ItemImage> itemImages = new ArrayList<ItemImage>();
				  List<ItemImage> allItemImage = itemImageRepo.findAll();
				  for (ItemImage i : allItemImage) {
					  if (i.getItemId() == itemID) {
						  itemImages.add(i);
					  }
				  }
				  return itemImages;
			  }
		  
}
