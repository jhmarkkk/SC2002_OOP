package enums;

public enum Role {
	
	STAFF,
	STUDENT,
	COMMITTEE;
	
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
