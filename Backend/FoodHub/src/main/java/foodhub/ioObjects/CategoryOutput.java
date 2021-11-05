package foodhub.ioObjects;

import foodhub.database.Category;

public class CategoryOutput {
	
	private long id;
	private String title;
	private String description;

	public CategoryOutput(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.description = category.getDescription();
	}

	public CategoryOutput(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
}
