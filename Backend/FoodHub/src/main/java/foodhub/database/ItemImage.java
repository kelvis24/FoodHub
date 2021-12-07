package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="itemImages")
public class ItemImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imageName;
	private String imageType;

	@Column(nullable = false, unique = true)
	private long itemId; //the ID of the firm that uploaded this image
	
	@Lob
	private byte[] data;

	public ItemImage(String imageName, String imageType, long itemId, byte[] data) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.itemId = itemId;
		this.data = data;
	}
	
	public ItemImage() {}

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

	public long getItemId() {
		return itemId;
	}

	public void setCategoryId(long itemId) {
		this.itemId = itemId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
