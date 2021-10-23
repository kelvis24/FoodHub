package foodhub.ioObjects;

public class AddItemInput extends Authentication {

	private String title;
	private ItemInfo data;
	
	public AddItemInput(String username, String password, String title, ItemInfo data) {
		super(username, password);
		this.title = title;
		this.data = data;
	}
	
	public AddItemInput() {
		super();
	}
	
	public String getTitle() {
		return title;
	}
	
	public ItemInfo getData() {
		return data;
	}
	
}
