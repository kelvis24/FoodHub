package foodhub.ioObjects;

public class AdminInput extends Authentication {
	
	private AdminInfo data;

	public AdminInput(String username, String password, AdminInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public AdminInput(String username, String password) {
		super(username, password);
	}
	
	public AdminInput() {
		super();
	}
	
	public AdminInfo getData() {
		return data;
	}
}
