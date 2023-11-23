/**
 * 
 */
package interfaces.dao;

import java.util.Map;

import models.Student;

/**
 * 
 */
public interface StudentDao {
	public Map<String, Student> getStudents();
	public void setStudents(Map<String, Student> students);
}
