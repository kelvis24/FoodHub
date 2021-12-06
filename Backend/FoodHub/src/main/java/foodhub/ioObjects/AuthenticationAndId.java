package foodhub.ioObjects;

public class AuthenticationAndId extends Authentication {
	
	private long id;
	
	public AuthenticationAndId(String username, String password, long id) {
		super(username, password);
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

}
