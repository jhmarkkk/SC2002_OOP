/**
 * 
 */
package dao;

import java.util.Map;

import models.CommitteeMember;

/**
 * 
 */
public class CommitteeMemberDaoImplementation implements CommitteeMemberDaoInterface {
	
	private Map<String, CommitteeMember> committeeMembers;

	/**
	 * @param committeeMembers
	 */
	public CommitteeMemberDaoImplementation(Map<String, CommitteeMember> committeeMembers) {
		this.committeeMembers = committeeMembers;
	}

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
		this.committeeMembers = committeeMembers;
	}


}
