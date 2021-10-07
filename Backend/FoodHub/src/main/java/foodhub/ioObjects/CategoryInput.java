package foodhub.ioObjects;

import foodhub.database.Category;

public class CategoryInput {
	private String username;
	private String password;
	private Category category;
	
	public CategoryInput(String username, String password, Category category) {
		this.username = username;
		this.password = password;
		this.category = category;
	}
	public CategoryInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Category getCategory() {
		return category;
	}
}
