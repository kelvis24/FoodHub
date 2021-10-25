package foodhub.ioObjects;

public class EditAdminInput extends AddAdminInput {
	
	private long adminId;

	public EditAdminInput(String username, String password, AdminInfo data, long adminId) {
		super(username, password, data);
		this.adminId = adminId;
	}
	
	public long getAdminId() {
		return adminId;
	}
	
}
