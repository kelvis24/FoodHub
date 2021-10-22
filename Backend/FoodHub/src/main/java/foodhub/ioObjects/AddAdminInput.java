package foodhub.ioObjects;

public class AddAdminInput extends Authentication {
	
	protected AdminInfo data;

	public AddAdminInput(String username, String password, AdminInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public AddAdminInput(String username, String password) {
		super(username, password);
	}
	
	public AddAdminInput() {
		super();
	}
	
	public AdminInfo getData() {
		return data;
	}
}
