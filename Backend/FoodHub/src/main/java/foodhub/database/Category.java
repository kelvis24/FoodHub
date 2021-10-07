package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String title;
	@Column(nullable = false, length = 100)
	private String descr;
	
    @ManyToOne
    @JoinColumn(name = "firm_id")
    @JsonIgnore
    private Firm firm;

	public Category(String title, String descr) {
		this.title = title;
		this.descr = descr;
	}
	
	public Category() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setUsername(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return descr;
	}
	
	public void setDescription(String description) {
		this.descr = description;
	}
	
    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

}
