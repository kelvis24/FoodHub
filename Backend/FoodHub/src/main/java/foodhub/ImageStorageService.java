package foodhub;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import foodhub.database.ImageRepository;
import foodhub.database.Images;

@Service
public class ImageStorageService {

	@Autowired
	private ImageRepository imageRepo;
	
	  public Images saveFile(MultipartFile file) {
		  String imageName = file.getOriginalFilename();
		  try {
			  Images image = new Images(imageName,file.getContentType(),file.getBytes());
			  return imageRepo.save(image);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  return null;
	  }
	  
	  public Optional<Images> getFile(Long fileId) {
		  return imageRepo.findById(fileId);
	  }
	  
	  public List<Images> getFiles(){
		  return imageRepo.findAll();
	  }
}
