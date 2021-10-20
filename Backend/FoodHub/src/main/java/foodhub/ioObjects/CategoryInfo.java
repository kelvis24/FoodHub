package foodhub.ioObjects;

import foodhub.database.Category;

public class CategoryInfo {
	
	private String title;
	private String description;

	public CategoryInfo(Category category) {
		this.title = category.getTitle();
		this.description = category.getTitle();
	}

	public CategoryInfo(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public CategoryInfo() {}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
