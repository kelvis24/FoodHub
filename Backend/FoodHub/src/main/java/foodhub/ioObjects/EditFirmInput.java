package foodhub.ioObjects;

public class EditFirmInput extends AddFirmInput {
	
	private String subject;

	public EditFirmInput(String username, String password, FirmInfo data, String subject) {
		super(username, password, data);
		this.subject = subject;
	}
	
	public EditFirmInput(String username, String password, FirmInfo data) {
		super(username, password, data);
	}
	
	public EditFirmInput() {
		super();
	}
	
	public String getSubject() {
		return subject;
	}
	
}
