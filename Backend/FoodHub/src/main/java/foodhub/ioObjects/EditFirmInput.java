package foodhub.ioObjects;

public class EditFirmInput extends AddFirmInput {

	private long firmId;

	public EditFirmInput(String username, String password, FirmInfo data, long firmId) {
		super(username, password, data);
		this.firmId = firmId;
	}
	
	public long getFirmId() {
		return firmId;
	}
	
}
