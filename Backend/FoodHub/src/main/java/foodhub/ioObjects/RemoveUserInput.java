package foodhub.ioObjects;

public class RemoveUserInput extends Authentication {
	
	private String user;

	public RemoveUserInput(String username, String password, String user) {
		super(username, password);
		this.user = user;
	}
	
	public RemoveUserInput(String username, String password) {
		super(username, password);
	}
	
	public RemoveUserInput() {
		super();
	}
	
	public String getUser() {
		return user;
	}
}