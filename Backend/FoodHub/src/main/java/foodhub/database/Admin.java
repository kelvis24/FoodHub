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
	
	public Admin(String name, String username, String password, int type) {
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
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", name=" + name + ", type=" + type + "]";
	}
}
