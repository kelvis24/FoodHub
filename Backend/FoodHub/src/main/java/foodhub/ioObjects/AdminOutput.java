package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminOutput {

	private String username;
	private String name;
	private int type;
	
	public AdminOutput(Admin admin) {
		this.username = admin.getUsername();
		this.name = admin.getUsername();
		this.type = admin.getType();
	}
	
	public AdminOutput(String username, String name, int type) {
		this.username = username;
		this.name = name;
		this.type = type;
	}

	public AdminOutput() {}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
}
