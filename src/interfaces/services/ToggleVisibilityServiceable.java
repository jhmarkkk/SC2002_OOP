package interfaces.services;

/**
 * The {@code ToggleVisibilityServiceable} interface represents an entity that provides the ability to toggle visibility.
 * 
 * <p>Classes implementing this interface should provide an implementation for the {@link #toggle()} method to handle the process of toggling visibility.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface ToggleVisibilityServiceable {

    /**
     * Performs the process of toggling visibility.
     */	
    public void toggle();
}
