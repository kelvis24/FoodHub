package foodhub.ioObjects;
import foodhub.database.Item;

public class ItemInput {
	private String username;
	private String password;
	private String category;
	private Item item;
	private String itemTitle;
	
	public ItemInput(String username, String password, String category, Item item) {
		this.username = username;
		this.password = password;
		this.category = category;
		this.item = item;
	}
	
	public ItemInput(String username, String password, String category, String itemTitle) {
		this.username = username;
		this.password = password;
		this.category = category;
		this.itemTitle = itemTitle;
	}

	public ItemInput() {}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
}
