package interfaces.services;

/**
 * The {@code ChangePasswordServiceable} interface defines a service for changing passwords.
 * 
 * <p>Classes that implement this interface should provide their own implementation for changing passwords, including validation and updating the password.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface ChangePasswordServiceable {
	
    /**
     * Attempts to change the password.
     * 
     * @return {@code true} if the password change is successful, {@code false} otherwise.
     */
    public boolean changePassword();
}
