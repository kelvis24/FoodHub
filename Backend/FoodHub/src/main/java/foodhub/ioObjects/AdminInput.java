package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminInput {
	
	private String username;
	private String password;
	private Admin data;

	public AdminInput(String username, String password, Admin data) {
		this.username = username;
		this.password = password;
		this.data = data;
	}
	
	public AdminInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Admin getData() {
		return data;
	}
	
}
