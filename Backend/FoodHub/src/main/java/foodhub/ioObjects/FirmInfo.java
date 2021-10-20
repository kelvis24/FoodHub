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

	public FirmInfo(String name, String username, String password, String location, String cuisine,
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

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getOpen_time() {
		return open_time;
	}

	public void setOpen_time(int open_time) {
		this.open_time = open_time;
	}

	public int getClose_time() {
		return close_time;
	}

	public void setClose_time(int close_time) {
		this.close_time = close_time;
	}

	public int getEmployee_count() {
		return employee_count;
	}

	public void setEmployee_count(int employee_count) {
		this.employee_count = employee_count;
	}
	
}
