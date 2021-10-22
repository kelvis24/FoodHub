package foodhub.ioObjects;

public class AddCategoryInput {
	private String username;
	private String password;
	private CategoryInfo data;
	
	public AddCategoryInput(String username, String password, CategoryInfo category) {
		this.username = username;
		this.password = password;
		this.data = category;
	}
	
	public AddCategoryInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public CategoryInfo getData() {
		return data;
	}
	
}
