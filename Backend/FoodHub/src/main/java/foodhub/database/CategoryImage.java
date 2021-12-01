package foodhub.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="categoryImages")
public class CategoryImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imageName;
	private String imageType;
	private long categoryId; //the ID of the firm that uploaded this image
	
	@Lob
	private byte[] data;

	public CategoryImage(String imageName, String imageType, long categoryId, byte[] data) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.categoryId = categoryId;
		this.data = data;
	}
	
	public CategoryImage() {}

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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
