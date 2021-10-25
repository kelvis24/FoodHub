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
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}

}
