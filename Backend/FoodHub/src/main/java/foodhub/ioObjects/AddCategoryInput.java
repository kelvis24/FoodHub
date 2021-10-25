package foodhub.ioObjects;

public class AddCategoryInput extends Authentication {

	private CategoryInfo data;
	
	public AddCategoryInput(String username, String password, CategoryInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public CategoryInfo getData() {
		return data;
	}
	
}
