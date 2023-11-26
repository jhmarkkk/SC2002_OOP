package enums;

/**
 * The {@code GenerateType} enum represents different filters for generating report.
 * 
 * The enum values include:
 * <ul>
 *   <li>{@code ALL}: Represents the generation of all items.</li>
 *   <li>{@code ATTENDEE}: Represents the generation specific to attendees.</li>
 *   <li>{@code COMMITTEE}: Represents the generation specific to committees.</li>
 * </ul>
 * 
 * @version 1.0
 * @since 1.0
 */
public enum GenerateType {

	/**
     * Represents the generation of all items.
     */
	ALL,

	/**
     * Represents the generation specific to attendees.
     */
	ATTENDEE,

    /**
     * Represents the generation specific to committees.
     */
	COMMITTEE;
}
