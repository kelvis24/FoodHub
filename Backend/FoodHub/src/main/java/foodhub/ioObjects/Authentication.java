package foodhub.ioObjects;

public class Authentication {
	
	protected String username;
	protected String password;

	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Authentication() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
