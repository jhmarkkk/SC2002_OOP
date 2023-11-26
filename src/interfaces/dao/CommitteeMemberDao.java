package interfaces.dao;

import java.util.Map;

import models.CommitteeMember;

/**
 * The {@code CommitteeMemberDao} interface defines methods for accessing and manipulating committee members-related data in the CAMs system. 
 * Implementations of this interface provide the underlying data access logic for retrieving and updating committee members information.
 * 
 * <p>It includes methods to get the map of committee members and set the map of committee members.
 * The map associates committee member IDs or names with corresponding {@link CommitteeMember} objects.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see CommitteeMember
 */
 public interface CommitteeMemberDao {
	
    /**
     * Retrieves the map of committee members associated with their IDs or names.
     * 
     * @return A map containing committee member IDs or names as keys and corresponding {@link CommitteeMember} objects as values.
     */
	public Map<String, CommitteeMember> getCommitteeMembers();

	/**
     * Sets the map of committee members.
     * 
     * @param committeeMembers A new map containing committee member IDs or names as keys and corresponding {@link CommitteeMember} objects as values.
     */
	public void setCommitteeMembers(Map<String, CommitteeMember> committeeMembers);
}
