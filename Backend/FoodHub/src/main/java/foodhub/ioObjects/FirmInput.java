package foodhub.ioObjects;

import foodhub.database.Firm;

public class FirmInput {
	
	private String username;
	private String password;
	private Firm data;
	private String firmName;

	public FirmInput(String username, String password, Firm data) {
		this.username = username;
		this.password = password;
		this.data = data;
	}
	
	public FirmInput(String username, String password, String firmName) {
		this.username = username;
		this.password = password;
		this.firmName = firmName;
	}
	
	public FirmInput() {}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Firm getData() {
		return data;
	}
	
	public String getFirmName() {
		return firmName;
	}
}
