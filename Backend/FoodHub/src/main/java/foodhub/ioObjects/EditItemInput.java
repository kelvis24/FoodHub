package foodhub.ioObjects;

public class EditItemInput extends AddItemInput {
	
	private String subject;

	public EditItemInput(String username, String password, String title, ItemInfo data, String subject) {
		super(username, password, title, data);
		this.subject = subject;
	}
	
	public EditItemInput(String username, String password, String title, ItemInfo data) {
		super(username, password, title, data);
	}
	
	public EditItemInput() {
		super();
	}
	
	public String getSubject() {
		return subject;
	}
	
}
