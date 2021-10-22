package foodhub.ioObjects;

public class RemoveEntitledInput extends Authentication {
	
	private String title;

	public RemoveEntitledInput(String username, String password, String title) {
		super(username, password);
		this.title = title;
	}
	
	public RemoveEntitledInput(String username, String password) {
		super(username, password);
	}
	
	public RemoveEntitledInput() {
		super();
	}
	
	public String getTitle() {
		return title;
	}
}