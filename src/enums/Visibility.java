
package enums;

public enum Visibility {
	
	ON,
	OFF;
	
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
