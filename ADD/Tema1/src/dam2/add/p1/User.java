package dam2.add.p1;

public class User {
	
	private String username;
	private String pass;
	private boolean valid;
	
	
	public User(String username, String pass, boolean valid) {
		super();
		this.username = username;
		this.pass = pass;
		this.valid = valid;
	}

	public User(String username, String pass) {
		super();
		this.username = username;
		this.pass = pass;
		this.setValid(true);
	}
	
	public User() {
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username+":"+pass;
	}
	
	

}
