package foodhub.ioObjects;

public class EditItemInput extends AddItemInput {

	private long itemId;

	public EditItemInput(String username, String password, long categoryId, ItemInfo data, long itemId) {
		super(username, password, categoryId, data);
		this.itemId = itemId;
	}
	
	public long getId() {
		return itemId;
	}
	
}
