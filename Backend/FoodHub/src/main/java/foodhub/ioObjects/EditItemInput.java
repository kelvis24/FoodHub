package foodhub.ioObjects;

public class EditItemInput extends Authentication {

	private long itemId;
	private ItemInfo data;

	public EditItemInput(String username, String password, long itemId, ItemInfo data) {
		super(username, password);
		this.itemId = itemId;
		this.data = data;
	}
	
	public long getItemId() {
		return itemId;
	}
	
	public ItemInfo getData() {
		return data;
	}
	
}
