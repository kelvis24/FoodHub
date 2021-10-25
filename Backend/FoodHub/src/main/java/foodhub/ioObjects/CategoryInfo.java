package foodhub.ioObjects;

public class CategoryInfo {
	
	private String title;
	private String description;

	public CategoryInfo(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
}
