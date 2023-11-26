package enums;

/**
 * The {@code SortType} enum represents different sorting types.
 * <p>
 * The enum values include:
 * <ul>
 *   <li>{@code NAME}: Sort by name.</li>
 *   <li>{@code DATES}: Sort by dates.</li>
 *   <li>{@code CLOSING_DATE}: Sort by closing dates.</li>
 *   <li>{@code LOCATION}: Sort by location.</li>
 *   <li>{@code FACULTY}: Sort by faculty.</li>
 *   <li>{@code STAFF}: Sort by staff.</li>
 * </ul>
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
public enum SortType {
	
	/**
     * Sort by name.
     */
	NAME,

    /**
     * Sort by dates.
     */
	DATES,

	/**
     * Sort by closing dates.
     */
	CLOSING_DATE,

	/**
     * Sort by location.
     */
	LOCATION,

    /**
     * Sort by faculty.
     */
	FACULTY,

	/**
     * Sort by staff.
     */
	STAFF;
}
