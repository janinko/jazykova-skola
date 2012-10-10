package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Lection;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface StudentDAO {
    public Student create(Student cours);
    public Student read(long id);
    public Student update(Student course);
    public Student delete(Student course);
    
    public List<Student> findAllStudents();
    public List<Student> findStudentByName(String firstName, String lastName);
    public List<Student> findStudentByLection(Lection lection);
}
