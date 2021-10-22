package foodhub.ioObjects;

public class AddCategoryInput extends Authentication {

	private CategoryInfo data;
	
	public AddCategoryInput(String username, String password, CategoryInfo category) {
		super(username, password);
		this.data = category;
	}
	
	public AddCategoryInput() {
		super();
	}
	
	public CategoryInfo getData() {
		return data;
	}
	
}
