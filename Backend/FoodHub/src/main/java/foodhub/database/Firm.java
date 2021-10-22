package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.FirmInfo;

@Entity
@Table(name="firms")
public class Firm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false, length = 100)
	private String location;
	@Column(nullable = false, length = 100)
	private String cuisine;
	@Column(nullable = false)
	private int open_time;
	@Column(nullable = false)
	private int close_time;
	@Column(nullable = false)
	private int employee_count;

	public Firm(FirmInfo firm) {
		this.username = firm.getUsername();
		this.password = firm.getPassword();
		this.name = firm.getName();
		this.location = firm.getLocation();
		this.cuisine = firm.getCuisine();
		this.open_time = firm.getOpen_time();
		this.close_time = firm.getClose_time();
		this.employee_count = firm.getEmployee_count();
	}
	
	public Firm(String username, String password, String name, String location,
				String cuisine, int open_time,   int close_time,  int employee_count) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
	}
	
	public Firm() {}

	public long getId() {
		return id;
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
