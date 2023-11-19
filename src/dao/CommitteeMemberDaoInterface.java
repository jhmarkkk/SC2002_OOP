/**
 * 
 */
package dao;

import java.util.Map;

import models.CommitteeMember;

/**
 * 
 */
public interface CommitteeMemberDaoInterface {
	public Map<String, CommitteeMember> getCommitteeMembers();
	public void setCommitteeMembers(Map<String, CommitteeMember> committeeMembers);
}
