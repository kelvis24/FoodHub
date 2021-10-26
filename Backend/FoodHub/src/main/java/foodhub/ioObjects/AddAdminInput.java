package foodhub.ioObjects;

public class AddAdminInput extends Authentication {
	
	private AdminInfo data;

	public AddAdminInput(String username, String password, AdminInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public AdminInfo getData() {
		return data;
	}
	
}
