/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.Collection;

/**
 *
 * @author xchrastk
 */
public interface StudentDAO {
    public Student create(Student cours);
    public Student read(long id);
    public Student update(Student course);
    public Student delete(Student course);
    
    public Collection<Student> findAllStudents();
}
