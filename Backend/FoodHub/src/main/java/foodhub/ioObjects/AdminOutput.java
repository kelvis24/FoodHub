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
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
}
