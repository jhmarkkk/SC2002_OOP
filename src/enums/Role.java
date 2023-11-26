package enums;

/**
 * The {@code Role} enum represents different roles that an entity can have.
 * <p>
 * The enum values include:
 * <ul>
 *   <li>{@code STAFF}: Represents the role of a staff member.</li>
 *   <li>{@code STUDENT}: Represents the role of a student.</li>
 *   <li>{@code COMMITTEE}: Represents the role of a committee member.</li>
 * </ul>
 * </p>
 * 
 * <p>The enum also provides a {@link #toString(Role)} method to convert a role to its corresponding string representation.</p>
 *  
 * @version 1.0
 * @since 1.0
 */
public enum Role {

	/**
     * Represents the role of a staff member.
     */
	STAFF,

	/**
     * Represents the role of a student.
     */
	STUDENT,

    /**
     * Represents the role of a committee member.
     */
	COMMITTEE;
	
    /**
     * Converts a {@code Role} enum value to its corresponding string representation.
     *
     * @param role the role to be converted to a string.
	 * 
     * @return the string representation of the role.
     */	
	public static String toString(Role role) {
		
		switch (role) {
			case STAFF:
				return "Staff";
			case STUDENT:
				return "Student";
			case COMMITTEE:
				return "Committee member";
			default:
				return "";
		}
	}
}
