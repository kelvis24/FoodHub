package foodhub.ioObjects;

import foodhub.database.Firm;

public class FirmOutput {

	private String username;
	private String name;
	private String location;
	private String cuisine;
	private int open_time;
	private int close_time;
	private int employee_count;

	public FirmOutput(Firm f) {
		this.name = f.getName();
		this.username = f.getUsername();
		this.location = f.getLocation();
		this.cuisine = f.getCuisine();
		this.open_time = f.getOpen_time();
		this.close_time = f.getClose_time();
		this.employee_count = f.getEmployee_count();
	}

	public FirmOutput(String name, String username, String location, String cuisine,
					int open_time,   int close_time,  int employee_count) {
		this.name = name;
		this.username = username;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
	}
	
	public FirmOutput() {}

	public String getUsername() {
		return username;
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
