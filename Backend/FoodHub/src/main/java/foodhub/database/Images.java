package foodhub.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="images")

public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imageName;
	private String imageType;
	private long firmId; //the ID of the firm that uploaded this image
	private long typeId; //the ID of the firm/category/item being referenced
	private int type; //0 == firm header image, 1 == category header image, 2 == item image
	
	@Lob
	private byte[] data;

	public Images(String imageName, String imageType, byte[] data, long firmID, long typeID, int type) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.data = data;
		this.firmId = firmID;
		this.typeId = typeID;
		this.type = type;
	}
	
	//used in the ImageStorageService
	public Images(String imageName, String imageType, byte[] data) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.data = data;
	}
	
	public Images() {
	}

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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getFirmId() {
		return firmId;
	}

	public void setFirmId(long firmId) {
		this.firmId = firmId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
