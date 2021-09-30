package foodhub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false)
	private int type;
	
	public Admin(String name, String email, String password, int type) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public Admin() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int  type) {
		this.type = type;
	}
	
}
