package interfaces.dao;

import java.util.Map;

import models.Student;


/**
 * The {@code StudentDao} interface defines methods for accessing and manipulating student information in the CAMs system.
 * Implementations of this interface provide the underlying data access logic for retrieving and updating student data.
 * 
 * <p>It includes methods to get the students and set the students. 
 * Students are represented by a {@link Student} object, containing details such as ID, name, and other relevant information.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see Student
 */
public interface StudentDao {
	
    /**
     * Retrieves the students from the data source.
     * 
     * @return A Map containing student ID as keys and corresponding {@link Student} objects as values.
     */
	public Map<String, Student> getStudents();
	
    /**
     * Sets the students in the data source.
     * 
     * @param students A new Map containing student ID as keys and corresponding {@link Student} objects as values.
     */
	public void setStudents(Map<String, Student> students);
}
