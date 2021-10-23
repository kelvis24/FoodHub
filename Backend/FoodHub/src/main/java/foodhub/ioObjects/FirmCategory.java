package foodhub.ioObjects;

public class FirmCategory extends Username {
	
	private String title;
	
	public FirmCategory(String username, String title) {
		super(username);
		this.title = title;
	}
	
	public FirmCategory() {
		super();
	}
	
	public String getTitle() {
		return title;
	}

}
