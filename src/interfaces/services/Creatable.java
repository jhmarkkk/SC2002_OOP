package interfaces.services;

/**
 * The {@code Creatable} interface defines a service for creating entities.
 * 
 * <p>Classes that implement this interface should provide their own implementation for creating entities.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Creatable {

    /**
     * Creates entities based on the specific implementation provided by the implementing class.
     * 
     * <p>Implementing classes should document any parameters or specific behavior associated with their entity creation process.</p>
     */
    public void create();
}
