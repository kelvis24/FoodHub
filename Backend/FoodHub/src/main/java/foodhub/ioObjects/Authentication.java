package foodhub.ioObjects;

public class Authentication extends Username {
	
	protected String password;

	public Authentication(String username, String password) {
		super(username);
		this.password = password;
	}
	
	public Authentication() {
		super();
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
