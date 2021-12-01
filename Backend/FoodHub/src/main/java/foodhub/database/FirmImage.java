package foodhub.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="firmImages")
public class FirmImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imageName;
	private String imageType;
	private long firmId; //the ID of the firm that uploaded this image
	
	@Lob
	private byte[] data;
	
	//used in the ImageStorageService
	public FirmImage(String imageName, String imageType, byte[] data, long firmId) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.data = data;
		this.firmId = firmId;
	}
	
	public FirmImage() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public long getFirmId() {
		return firmId;
	}

	public void setFirmId(long firmId) {
		this.firmId = firmId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
