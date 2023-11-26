package models;

import enums.Role;

/**
 * The {@link User} class represents a user in CAMs.
 * Users have a unique ID, password, name, and faculty.
 * Each user also has a {@link Role} which determines their level of access.
 * 
 * <p>This class provides a basic structure for user-related functionalities within the CAMs system.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 3.0
 * @since 1.0
 */
public class User {
	/**
	 * The unique ID of the user.
	 */
	private String userID;

	/**
	 * The password for user authentication.
	 * Default value is "password".
	 */
	private String password = "password";

	/**
	 * The name of the user.
	 */
	private String name;

	/**
	 * The faculty to which the user belongs.
	 */
	private String faculty;
	
	/**
	 * The {@link Role} of the user determining their level of access.
	 */
	private Role role;
	
	/**
     * Constructs a {@link User} object with the provided userID, password, name, faculty, and role.
	 * 
	 * @param userID 	The unique ID of the user.
	 * @param password 	The password of the user.
	 * @param name 		The name of the user.
	 * @param faculty 	The faculty to which the user belongs.
	 * @param role 		The {@link Role} of the user.
	 */
	public User(String userID, String password, String name, String faculty, Role role) {
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.faculty = faculty;
		this.role = role;
	}

	/**
	 * Returns the unique user ID of the user.
	 * 
	 * @return The user ID of the user.
	 */
	public String getUserID() {
		
		return userID;
	}

	/**
	 * Sets the unique ID of the user.
	 * 
	 * @param userID The new user ID of the user to set.
	 */
	public void setUserID(String userID) {
		
		this.userID = userID;
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return The password of the user.
	 */
	public String getPassword() {
		
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password The new password to set for the user.
	 */
	public void setPassword(String password) {
		
		this.password = password;
	}

	/**
	 * Returns the name of the user.
	 * 
	 * @return The name of the user.
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name The new name of the user to set.
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Returns the faculty to which the user belongs.
	 * 
	 * @return The faculty to which the user belongs to.
	 */
	public String getFaculty() {
		
		return faculty;
	}

	/**
	 * Sets the faculty to which the user belongs.
	 * 
	 * @param faculty The new faculty to which the user belongs to.
	 */
	public void setFaculty(String faculty) {
		
		this.faculty = faculty;
	}

	/**
	 * Returns the {@link Role} of the user.
	 * 
	 * @return The role of the user.
	 */
	public Role getRole() {
		
		return role;
	}

	/**
	 * Sets the {@link Role} of the user.
	 * 
	 * @param role The new role of the user.
	 */
	public void setRole(Role role) {
		
		this.role = role;
	}
}
