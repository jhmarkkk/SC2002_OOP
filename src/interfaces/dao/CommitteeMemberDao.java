/**
 * 
 */
package interfaces.dao;

import java.util.Map;

import models.CommitteeMember;

/**
 * 
 */
public interface CommitteeMemberDao {
	public Map<String, CommitteeMember> getCommitteeMembers();
	public void setCommitteeMembers(Map<String, CommitteeMember> committeeMembers);
}
