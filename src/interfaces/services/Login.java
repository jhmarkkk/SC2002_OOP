package interfaces.services;

/**
 * The {@code Login} interface represents an entity that provides login functionality.
 * 
 * <p>Classes implementing this interface should provide an implementation for the {@link #login()} method to handle the login process.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Login {
	
    /**
     * Performs the login process.
     */
    public void login();
}
