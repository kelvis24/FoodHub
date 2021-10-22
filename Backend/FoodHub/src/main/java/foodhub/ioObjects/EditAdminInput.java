package foodhub.ioObjects;

public class EditAdminInput extends AddAdminInput {
	
	private String subject;

	public EditAdminInput(String username, String password, AdminInfo data, String subject) {
		super(username, password, data);
		this.subject = subject;
	}
	
	public EditAdminInput(String username, String password, AdminInfo data) {
		super(username, password, data);
	}
	
	public EditAdminInput() {
		super();
	}
	
	public String getSubject() {
		return subject;
	}
	
}
