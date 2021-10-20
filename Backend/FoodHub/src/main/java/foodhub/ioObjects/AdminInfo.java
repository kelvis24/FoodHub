package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminInfo {

	private String username;
	private String password;
	private String name;
	
	public AdminInfo(Admin admin) {
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.name = admin.getUsername();
	}
	
	public AdminInfo(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public AdminInfo() {}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
