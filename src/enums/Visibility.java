
package enums;

/**
 * The {@code Visibility} enum represents different visibility states.
 * <p>
 * The enum values include:
 * <ul>
 *   <li>{@code ON}: Represents the "On" visibility state.</li>
 *   <li>{@code OFF}: Represents the "Off" visibility state.</li>
 * </ul>
 * </p>
 * 
 * <p>The enum also provides a {@link #toString(Visibility)} method to convert a visibility state to its corresponding string representation.</p>
 * 
 * @version 1.0
 * @since 1.0
 */
public enum Visibility {
	
	/**
     * Represents the "On" visibility state.
     */
	ON,

	/**
     * Represents the "Off" visibility state.
     */
	OFF;
	
	/**
     * Converts a {@code Visibility} enum value to its corresponding string representation.
     *
     * @param visibility the visibility state to be converted to a string.
	 * 
     * @return the string representation of the visibility state.
     */
	public static String toString(Visibility visibility) {
		switch (visibility) {
			case ON:
				return "On";
			case OFF:
				return "Off";
			default:
				return "";
		}
	}
}
