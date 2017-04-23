package auto.deploy.dao.entity;

public class User extends Entity<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5176462817657973359L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
