package foodhub.ioObjects;

public class CustomerInfo {
	
	private String username;
	private String password;
	private String name;
	private String location;
	
	public CustomerInfo(String username, String password, String name, String location) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
	}
	
	public CustomerInfo() {}
	
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
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

}
