package foodhub.ioObjects;

/**
 * An input protocol used in editing a firm
 * @author 1_CW_2
 *
 */
public class EditFirmInput extends AddFirmInput {

	private long firmId;

	/**
	 * Constructs a new EditFirmInput object from enumerated information
	 * @param username The username of the admin editing the firm
	 * @param password The password fo the admin editing the firm
	 * @param data The new qualities of the firm
	 * @param firmId The id of the firm which is to be edited
	 * @see AddFirmInput
	 */
	public EditFirmInput(String username, String password, FirmInfo data, long firmId) {
		super(username, password, data);
		this.firmId = firmId;
	}
	
	/**
	 * a getter for the firmId field
	 * @return The id of the firm which is to be edited
	 */
	public long getFirmId() {
		return firmId;
	}
	
}
