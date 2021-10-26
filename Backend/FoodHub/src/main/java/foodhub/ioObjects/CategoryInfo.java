package foodhub.ioObjects;

import foodhub.database.Category;

public class CategoryInfo {
	
	private Long firmId;
	private String title;
	private String description;

	public CategoryInfo(Category category) {
		this.firmId = category.getFirmId();
		this.title = category.getTitle();
		this.description = category.getTitle();
	}

	public CategoryInfo(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public CategoryInfo() {}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Long getFirmId() {
		return firmId;
	}
	
}
