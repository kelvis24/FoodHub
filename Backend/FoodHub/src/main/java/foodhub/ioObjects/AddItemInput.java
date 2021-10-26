package foodhub.ioObjects;

public class AddItemInput extends Authentication {

	private long categoryId;
	private ItemInfo data;
	
	public AddItemInput(String username, String password, long categoryId, ItemInfo data) {
		super(username, password);
		this.categoryId = categoryId;
		this.data = data;
	}
	
	public long getCategoryId() {
		return categoryId;
	}
	
	public ItemInfo getData() {
		return data;
	}
	
}
