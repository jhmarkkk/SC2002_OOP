package assignment_CAMsApp;

enum Role{
	STAFF,
	STUDENT,
	COMMITTEE
}

public class User {
	private String userID;
	private String password;
	private String facultyInfo;
	private Role role;
	
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String set_password) {
		password = set_password;
	}
	
	public String getFacultyInfo() {
		return facultyInfo;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role set_role) {
		role = set_role;
	}
}
