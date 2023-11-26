package interfaces.services;

/**
 * The {@code Importable} interface represents an entity that can be imported from a specified file path.
 * 
 * <p>Classes that implement this interface should provide their own implementation for importing data based on the requirements of the specific application.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Importable {

	/**
     * Imports data from the specified file path.
     *
     * @param filePath the path to the file from which data should be imported.
     */
    public void importing (String filePath);
}
