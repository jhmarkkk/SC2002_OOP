/**
 * 
 */
package dao;

import java.util.Map;

import models.Student;

/**
 * 
 */
public interface StudentDaoInterface {
	public Map<String, Student> getStudents();
	public void setStudents(Map<String, Student> students);
}
