package interfaces.views;

/**
 * The {@code ProfileViewable} interface extends the {@link Viewable} interface and defines a method for displaying a profile view.
 * 
 * <p>Classes that implement this interface should provide their own implementation for the {@code view()} method, specifying how the profile information is presented.</p>
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public interface ProfileViewable extends Viewable {

    /**
     * Displays the profile information in a specific manner.
     */
    public void view();
}
