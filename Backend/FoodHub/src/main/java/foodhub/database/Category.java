package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false, unique = true, length = 100)
	private String title;
	@Column(nullable = false, length = 100)
	private String descr;
	

	public Category(long firmId, String title, String descr) {
		this.firmId = firmId;
		this.title = title;
		this.descr = descr;
	}
	
	public Category() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getFirmId() {
		return firmId;
	}
	
	public void setFirmId(long firmId) {
		this.firmId = firmId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return descr;
	}
	
	public void setDescription(String description) {
		this.descr = description;
	}

}
