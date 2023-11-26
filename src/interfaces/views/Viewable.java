package interfaces.views;


/**
 * The {@code Viewable} interface defines a method for displaying the information of implementing classes. 
 * Classes that implement this interface provide their own implementation for the {@code view()} method, specifying how their information is presented.
 * 
 * <p>Implementations of this interface are expected to offer a visual representation or display of their internal state or relevant details.</p>
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public interface Viewable {
	
    /**
     * Displays the information or state of the implementing class.
     */
    void view();
}