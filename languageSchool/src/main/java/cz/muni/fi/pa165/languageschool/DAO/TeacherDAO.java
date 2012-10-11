package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface TeacherDAO {
    public Teacher create(Teacher teacher);
    public Teacher read(long id);
    public Teacher update(Teacher teacher);
    public Teacher delete(Teacher teacher);
    
    public List<Teacher> findAllTeachers();
    public List<Teacher> findTeacherByName(String firstName, String lastName);
    public List<Teacher> findTeacherByLanguage(Language language);
}
