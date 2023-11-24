/**
 * 
 */
package dao;

import java.util.HashMap;
import java.util.Map;

import interfaces.dao.StudentDao;
import models.Student;

/**
 * 
 */
public class StudentDaoImpl implements StudentDao {
	
	private static Map<String, Student> students = new HashMap<String, Student>();

	/**
	 * @return the students
	 */
	public Map<String, Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(Map<String, Student> students) {
		StudentDaoImpl.students = students;
	}
	
	
}
