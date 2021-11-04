package foodhub.ioObjects;

import foodhub.database.Admin;

public class AdminOutput {
	
	private long id;
	private String username;
	private String name;
	private int type;
	
	public AdminOutput(Admin admin) {
		this.id = admin.getId();
		this.username = admin.getUsername();
		this.name = admin.getName();
		this.type = admin.getType();
	}
	
	public AdminOutput(long id, String username, String name, int type) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	
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
