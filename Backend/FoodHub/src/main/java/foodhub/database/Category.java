package foodhub.database;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private long firm_id;
	@Column(nullable = false, unique = true, length = 100)
	private String title;
	@Column(nullable = false, unique = true, length = 100)
	private String desc;
	public Category(long id, long firm_id, String title, String desc) {
		super();
		this.id = id;
		this.firm_id = firm_id;
		this.title = title;
		this.desc = desc;
	}
	public Category() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFirm_id() {
		return firm_id;
	}
	public void setFirm_id(long firm_id) {
		this.firm_id = firm_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
