package foodhub.ioObjects;

public class EditCategoryInput extends AddCategoryInput {
	
	private long categoryId;

	public EditCategoryInput(String username, String password, CategoryInfo data, long categoryId) {
		super(username, password, data);
		this.categoryId = categoryId;
	}

	public long getCategoryId() {
		return categoryId;
	}
	
}
