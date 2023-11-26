package interfaces.services;

/**
 * The {@code Exportable} interface defines a method for exporting data to a specified file path.
 * 
 * <p>Classes that implement this interface should provide their own implementation for exporting data based on the requirements of the specific application.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface Exportable {
    /**
     * Exports data from the specified file path.
     *
     * @param filePath the path to the file from which data should be exported.
     */
    public void exporting (String filePath);
}
