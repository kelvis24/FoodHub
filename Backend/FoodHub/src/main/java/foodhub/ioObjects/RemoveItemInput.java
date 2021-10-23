package foodhub.ioObjects;

public class RemoveItemInput extends Authentication {
	
	private String categoryTitle;
	private String itemTitle;

	public RemoveItemInput(String username, String password, String categoryTitle, String itemTitle) {
		super(username, password);
		this.categoryTitle = categoryTitle;
		this.itemTitle = itemTitle;
	}
	
	public RemoveItemInput(String username, String password) {
		super(username, password);
	}
	
	public RemoveItemInput() {
		super();
	}
	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
}