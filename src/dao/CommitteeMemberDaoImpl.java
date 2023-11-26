package dao;

import java.util.HashMap;
import java.util.Map;

import interfaces.dao.CommitteeMemberDao;

import models.CommitteeMember;

/**
 * The {@code CommitteeMemberDaoImpl} class is an implementation of the {@link CommitteeMemberDao} interface.
 * It provides methods to manage and access committee members stored in a map.
 *  
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see CommitteeMemberDao
 * @see models.CommitteeMember
 */
public class CommitteeMemberDaoImpl implements CommitteeMemberDao {
	
	private static Map<String, CommitteeMember> committeeMembers = new HashMap<String, CommitteeMember>();

    /**
     * Retrieves the map of committee members stored in the DAO.
     * 
     * @return the map of committee members, where the keys are unique identifiers and the values are committee member objects.
     */
	public Map<String, CommitteeMember> getCommitteeMembers() {
		return committeeMembers;
	}

    /**
     * Sets the map of committee members in the DAO.
     * 
     * @param committeeMembers the new map of committee members to set, where the keys are unique identifiers and the values are committee member objects.
     */
	public void setCommitteeMembers(Map<String, CommitteeMember> committeeMembers) {
		CommitteeMemberDaoImpl.committeeMembers = committeeMembers;
	}


}
