package dao;

import java.util.HashMap;
import java.util.Map;

import interfaces.dao.StudentDao;

import models.Student;

/**
 * The {@code StudentDaoImpl} class is an implementation of the {@link StudentDao} interface.
 * It manages and provides access to student objects stored in a map.
 *  
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see StudentDao
 */
public class StudentDaoImpl implements StudentDao {
	
	/**
     * A map to store student objects with their User ID as keys.
     */
	private static Map<String, Student> students = new HashMap<String, Student>();

    /**
     * Retrieves the map of students stored in the DAO.
     * 
     * @return the map of students, where the keys are unique identifiers and the values are student objects.
     */
	public Map<String, Student> getStudents() {
		return students;
	}

    /**
     * Sets the map of students in the DAO, useful for initializing the DAO with an existing set of students or updating it.
     * 
     * @param students the map of students to set, where the keys are unique identifiers and the values are student objects.
     */
	public void setStudents(Map<String, Student> students) {
		StudentDaoImpl.students = students;
	}
	
}
