package interfaces.services;

/**
 * The {@code ReplyEnquiryServiceable} interface represents an entity that provides the ability to reply to inquiries or messages.
 * 
 * <p>Classes implementing this interface should provide an implementation for the {@link #reply()} method to handle the process of replying to inquiries or messages.</p>
 *
 * @author Huang Caihong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0 
 */
public interface ReplyEnquiryServiceable {
    /**
     * Performs the process of replying to inquiries or messages.
     */
	public void reply();
}
