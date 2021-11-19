package foodhub.ioObjects;

public class NameAndLocation {
	
	private String name;
	private String location;
	
	public NameAndLocation(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public NameAndLocation() {
		this.name = "null";
		this.location = "null";
	}
	
	public String getTitle() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}

}
