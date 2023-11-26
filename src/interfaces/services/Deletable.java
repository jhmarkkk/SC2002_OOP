package interfaces.services;

/**
 * The {@code Deletable} interface defines a method for deleting entities or data.
 * 
 * <p>Classes that implement this interface should provide their own implementation for the delete operation based on the requirements of the specific application.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Deletable {
    /**
     * Performs the deletion operation based on the requirements of the specific application.
     */
    public void delete();
}
