package Final;


//Account class representing the blueprint of a user account

public class Account {
	private String username;
	private String password;
	 String name;
	 String number;
	
	public Account(String username, String password,String name,String number) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.number = number;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	
	public String getPassword() {
		return password;
	}
}

