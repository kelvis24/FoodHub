package foodhub.ioObjects;

public class ImageInput extends AuthenticationAndId {
	
	private String data;

	public ImageInput(String username, String password, long id, String data) {
		super(username, password, id);
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
}
