package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.services.*;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;

/**
 *
 * @author fivekeyem
 */
public interface StudentService {
    
    public void update(Student student);
	
	public Student read(String email);
	
	public List<Lesson> getAllLections(Student student);
	
	public void lectionEnroll(Student student, Lesson lesson);
	
	public void lectionCancel(Student student, Lesson lesson);
	
}
