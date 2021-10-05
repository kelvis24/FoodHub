package foodhub.ioObjects;

import foodhub.database.Firm;

public class FirmInput {
	
	private String username;
	private String password;
	private Firm data;

	public FirmInput(String username, String password, Firm data) {
		this.username = username;
		this.password = password;
		this.data = data;
	}
	
	public FirmInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Firm getData() {
		return data;
	}
	
}
