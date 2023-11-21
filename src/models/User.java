package models;

import enums.Role;


public class User {
	//private String userID;
	private String password = "password";
	private String name;
	private String faculty;
	private Role role;
	
	//public User(String userID, String password, String name, String faculty, Role role) {
	public User(String password, String name, String faculty, Role role) {
        //this.userID = userID;
		this.password = password;
		this.name = name;
		this.faculty = faculty;
		this.role = role;
	}

	// public String getUserID() {
	// 	return userID;
	// }

	// public void setUserID(String userID) {
	// 	this.userID = userID;
	// }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void viewProfile() {
		
	}
	
}