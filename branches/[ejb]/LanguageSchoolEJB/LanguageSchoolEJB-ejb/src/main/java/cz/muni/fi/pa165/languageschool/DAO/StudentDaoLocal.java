/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.ejb.Local;

/**
 *
 * @author fivekeyem
 */
@Local
public interface StudentDaoLocal {
	
	/**
	 * This method save new student.
	 * 
	 * @param new student
	 * @return new student who is in database now
	 * @throws IllegalArgumentException if student is null
	 */
    public Student create(Student student);
	
	
	/**
	 * This method return student.
	 * 
	 * @param id unique number of student
	 * @return Student by id
	 * @throws IllegalArgumentException if there is id null or id <= 0
	 */
    public Student read(long id);
	
}
