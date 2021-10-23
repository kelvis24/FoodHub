package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.CategoryInfo;
import foodhub.ioObjects.Entitled;

@Entity
@Table(name="categories")
public class Category implements Entitled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false, length = 100)
	private String title;
	@Column(nullable = false, length = 100)
	private String description;
	
	public Category(long firmId, CategoryInfo category) {
		this.firmId = firmId;
		this.title = category.getTitle();
		this.description = category.getDescription();
	}

	public Category(long firmId, String title, String description) {
		this.firmId = firmId;
		this.title = title;
		this.description = description;
	}
	
	public Category() {}
	
	public long getId() {
		return id;
	}
	
	public Long getFirmId() {
		return firmId;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
}
