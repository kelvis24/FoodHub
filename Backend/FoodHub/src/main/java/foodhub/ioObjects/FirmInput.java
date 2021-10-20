package foodhub.ioObjects;

public class FirmInput extends Authentication {
	
	private FirmInfo data;

	public FirmInput(String username, String password, FirmInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public FirmInput(String username, String password) {
		super(username, password);
	}
	
	public FirmInput() {
		super();
	}
	
	public FirmInfo getData() {
		return data;
	}
	
}
