package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface TeacherDAO {
    public Teacher create(Teacher course);
    public Teacher read(long id);
    public Teacher update(Teacher course);
    public Teacher delete(Teacher course);
    
    public List<Teacher> findAllTeachers();
    public List<Teacher> findTeacherByName(String firstName, String lastName);
    public List<Teacher> findTeacherByLanguage(Language language);
}
