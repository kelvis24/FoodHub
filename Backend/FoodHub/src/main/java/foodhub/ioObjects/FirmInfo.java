package foodhub.ioObjects;

public class FirmInfo {

	private String username;
	private String password;
	private String name;
	private String location;
	private String cuisine;
	private int open_time;
	private int close_time;
	private int employee_count;

	public FirmInfo(String username, String password, String name, String location, String cuisine,
					int open_time,   int close_time,  int employee_count) {
		this.name = name;
		this.password = password;
		this.username = username;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
	}
	
	public FirmInfo() {}

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

	public String getCuisine() {
		return cuisine;
	}

	public int getOpen_time() {
		return open_time;
	}

	public int getClose_time() {
		return close_time;
	}

	public int getEmployee_count() {
		return employee_count;
	}
	
}
