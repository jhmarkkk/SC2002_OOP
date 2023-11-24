package models;

import enums.Role;

/**
 * The {@link User} class represents a user in CAMs.
 * Users have a unique ID, password, name, and faculty.
 * Each user also has a {@link Role} which determines their level of access.
 */
public class User {
	/**
	 * The ID of the user.
	 */
	private String userID;
	/**
	 * The password of the user.
	 */
	private String password = "password";
	/**
	 * The name of the user.
	 */
	private String name;
	/**
	 * The faculty of the user.
	 */
	private String faculty;
	/**
	 * The {@link Role} of the user.
	 */
	private Role role;
	
	/**
	 * Constructs a {@link User} object with given userID, password, name, faculty and role.
	 * @param userID the ID of the user
	 * @param password the password of the user
	 * @param name the name of the user
	 * @param faculty the faculty of the user
	 * @param role the {@link Role} of the user
	 */
	public User(String userID, String password, String name, String faculty, Role role) {
		
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.faculty = faculty;
		this.role = role;
	}

	/**
	 * Returns the user ID of the user.
	 * 
	 * @return the userID
	 */
	public String getUserID() {
		
		return userID;
	}

	/**
	 * Sets the user ID of the user.
	 * 
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		
		this.userID = userID;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		
		this.password = password;
	}

	/**
	 * Returns the name of the user.
	 * 
	 * @return the name
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Returns the faculty of the user.
	 * 
	 * @return the faculty
	 */
	public String getFaculty() {
		
		return faculty;
	}

	/**
	 * Sets the faculty of the user.
	 * 
	 * @param faculty the faculty to set
	 */
	public void setFaculty(String faculty) {
		
		this.faculty = faculty;
	}

	/**
	 * Returns the {@link Role} of the user.
	 * 
	 * @return the role
	 */
	public Role getRole() {
		
		return role;
	}

	/**
	 * Sets the {@link Role} of the user.
	 * 
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		
		this.role = role;
	}
}
