package foodhub.ioObjects;

public class RemoveCategoryInput extends Authentication {
	
	private String title;

	public RemoveCategoryInput(String username, String password, String title) {
		super(username, password);
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}