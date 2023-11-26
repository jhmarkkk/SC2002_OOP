package interfaces.services;

/**
 * The {@code Logout} interface represents an entity that provides logout functionality.
 * 
 * <p>Classes implementing this interface should provide an implementation for the {@link #logout()} method to handle the logout process.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Logout {
    /**
     * Performs the logout process.
     */
    public void logout();
}
