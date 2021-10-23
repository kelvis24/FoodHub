package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.AdminInfo;

@Entity
@Table(name="admins")
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false)
	private int type; // 0 => Admin; 1 => Owner
	
	public Admin(AdminInfo admin) {
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.name = admin.getName();
		this.type = 0;
	}
	
	public Admin(String username, String password, String name, int type) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
	}

	public Admin() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public int getType() {
		return type;
	}
	
}
