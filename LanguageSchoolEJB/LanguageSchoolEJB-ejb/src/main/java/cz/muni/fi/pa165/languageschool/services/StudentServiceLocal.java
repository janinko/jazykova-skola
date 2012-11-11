package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.ejb.Local;

/**
 *
 * @author fivekeyem
 */
@Local
public interface StudentServiceLocal {
	void createStudent(Student student);
    
    void readStudent(Student student);
}
