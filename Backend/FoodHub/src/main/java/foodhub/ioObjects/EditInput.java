package foodhub.ioObjects;

public class EditInput {
	private String username;
	private String password;
	private int field;
	private String fieldInfo;
	private String fieldName;
	
	public EditInput(String username, String password, int field, String fieldInfo) {
		this.username = username;
		this.password = password;
		this.field = field;
		this.fieldInfo = fieldInfo;
	}
	
	public EditInput(String username, String password, String fieldName, int field, String fieldInfo) {
		this.username = username;
		this.password = password;
		this.field = field;
		this.fieldInfo = fieldInfo;
		this.fieldName = fieldName;
	}
	
	public EditInput() {}

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

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	public String getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
