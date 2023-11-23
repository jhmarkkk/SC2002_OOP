/**
 * 
 */
package dao;

import java.util.Map;

import interfaces.dao.CommitteeMemberDao;
import models.CommitteeMember;

/**
 * 
 */
public class CommitteeMemberDaoImpl implements CommitteeMemberDao {
	
	private static Map<String, CommitteeMember> committeeMembers;

	/**
	 * @return the committeeMembers
	 */
	public Map<String, CommitteeMember> getCommitteeMembers() {
		return committeeMembers;
	}

	/**
	 * @param committeeMembers the committeeMembers to set
	 */
	public void setCommitteeMembers(Map<String, CommitteeMember> committeeMembers) {
		CommitteeMemberDaoImpl.committeeMembers = committeeMembers;
	}


}
