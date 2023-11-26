package interfaces.services;

/**
 * The {@code Editable} interface defines a method for editing entities or data.
 * 
 * <p>Classes that implement this interface should provide their own implementation for the edit operation based on the requirements of the specific application.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Editable {
    
    /**
     * Performs the edit operation based on the requirements of the specific application.
     */	
    public void edit();
}
