package user;

public class User {
	private String id;
	private String name;
	private String uname;

	public User(String id, String name, String uname) {
		this.id = id;
		this.name = name;
		this.uname = uname;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUname() {
		return uname;
	}
}
