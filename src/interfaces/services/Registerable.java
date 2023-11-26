package interfaces.services;

/**
 * The {@code Registerable} interface represents an entity that provides registration functionality.
 * 
 * <p>Classes implementing this interface should provide an implementation for the {@link #register()} method to handle the registration process.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Registerable {

    /**
     * Performs the registration process.
     */
    public void register();
}
