package foodhub.ioObjects;

public class Authentication extends Username {
	
	private String password;

	public Authentication(String username, String password) {
		super(username);
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
