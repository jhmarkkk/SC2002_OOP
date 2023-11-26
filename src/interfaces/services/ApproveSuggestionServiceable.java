package interfaces.services;

/**
 * The {@code ApproveSuggestionServiceable} interface defines a service for approving suggestions.
 * 
 * <p>Classes that implement this interface should provide their own implementation for the approval process of suggestions.</p>
 * 
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface ApproveSuggestionServiceable {
    
    /**
     * Performs the approval process for suggestions.
     */
    public void approve();
}
