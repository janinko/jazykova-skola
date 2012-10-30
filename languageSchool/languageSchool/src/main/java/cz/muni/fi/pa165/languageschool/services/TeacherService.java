package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface TeacherService {
    /*
     * Adds language to a teacher.
     * @param language language to be added to the teacher
     * @param id id of the teacher
     */
    void addLanguageToTeacher(Language language, Long id);
    
    /*
     * Adds a new teacher to the database. The teacher must not be null.
     * 
     * @param teacher teacher to be added
     * @return teacher the new teacher (retrieved from the database)
     * @throws IllegalArgumentException if the teacher is null
     */   
    void create(Teacher teacher);
    
    Teacher read(Long id);
    
    void update(Teacher teacher);    
    
    void delete(Teacher teacher);
    
    public List<Teacher> findAllTeachers();
    
    public List<Teacher> findTeacherByName(String firstName, String lastName);
    
    public List<Teacher> findTeacherByLanguage(Language language);    
}
