package foodhub.ioObjects;

public class RemoveEntity extends Authentication {

	private long id;

	public RemoveEntity(String username, String password, long id) {
		super(username, password);
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
}