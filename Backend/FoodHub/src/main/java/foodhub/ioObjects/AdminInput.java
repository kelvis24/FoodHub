package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminInput {
	
	private String userName;
	private String password;
	private Admin data;

	public AdminInput(String userName, String password, Admin data) {
		this.userName = userName;
		this.password = password;
		this.data = data;
	}
	
	public AdminInput() {}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Admin getData() {
		return data;
	}
	
}
