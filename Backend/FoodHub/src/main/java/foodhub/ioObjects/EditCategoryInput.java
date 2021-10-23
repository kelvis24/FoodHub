package foodhub.ioObjects;

public class EditCategoryInput extends AddCategoryInput {
	
	private String subject;

	public EditCategoryInput(String username, String password, CategoryInfo data, String subject) {
		super(username, password, data);
		this.subject = subject;
	}
	
	public EditCategoryInput(String username, String password, CategoryInfo data) {
		super(username, password, data);
	}
	
	public EditCategoryInput() {
		super();
	}
	
	public String getSubject() {
		return subject;
	}
	
}
