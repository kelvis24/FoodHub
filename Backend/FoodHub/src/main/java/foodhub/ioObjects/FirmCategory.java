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
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

}
