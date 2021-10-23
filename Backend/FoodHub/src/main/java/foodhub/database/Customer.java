package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.CustomerInfo;

@Entity
@Table(name="customers")
public class Customer {
	
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
	
	public Customer(CustomerInfo customer) {
		this.username = customer.getUsername();
		this.password = customer.getPassword();
		this.name = customer.getName();
		this.location = customer.getLocation();
	}
	
	public Customer(String username, String password, String name, String location) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.location = location;
	}
	
	public Customer() {}
	
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

}
