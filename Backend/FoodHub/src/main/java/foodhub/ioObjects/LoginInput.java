package foodhub.ioObjects;

public class LoginInput {
	
	private String username;
	private String password;

	public LoginInput(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
