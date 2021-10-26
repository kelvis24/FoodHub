package foodhub.ioObjects;

public class AddFirmInput extends Authentication {
	
	private FirmInfo data;

	public AddFirmInput(String username, String password, FirmInfo data) {
		super(username, password);
		this.data = data;
	}
	
	public FirmInfo getData() {
		return data;
	}
	
}
