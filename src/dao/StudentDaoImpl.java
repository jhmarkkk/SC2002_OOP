/**
 * 
 */
package dao;

import java.util.Map;

import models.Student;

/**
 * 
 */
public class StudentDaoImpl implements StudentDao {
	
	private static Map<String, Student> students;

	/**
	 * @param students
	 */
	public StudentDaoImpl(Map<String, Student> students) {
		this.students = students;
	}

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
		this.students = students;
	}
	
	
}
