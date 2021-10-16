package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminInput {
	
	private String username;
	private String password;
	private Admin data;
	private String adminUsername;

	public AdminInput(String username, String password, Admin data) {
		this.username = username;
		this.password = password;
		this.data = data;
	}
	
	public AdminInput(String username, String password, String adminUsername) {
		this.username = username;
		this.password = password;
		this.adminUsername = adminUsername;
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
	
	public String getAdminUsername() {
		return adminUsername;
	}
}
