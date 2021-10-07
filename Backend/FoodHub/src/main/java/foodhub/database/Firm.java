package foodhub.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	public Firm(String name, String username, String password, String location,
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

	public void setId(long id) {
		this.id = id;
	}

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
