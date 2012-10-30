package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.TeacherDAO;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public class TeacherServiceImpl implements TeacherService {

    private TeacherDAO dao;

    public void setDao(TeacherDAO dao) {
        this.dao = dao;
    }

    public void addLanguageToTeacher(Language language, Long id) {
        Teacher t = dao.read(id);                
        t.getLanguages().add(language);
    }

    public void create(Teacher teacher) {
        dao.create(teacher);
    }

    public Teacher read(Long id) {
        return dao.read(id);
    }

    public void update(Teacher teacher) {
        dao.update(teacher);
    }

    public void delete(Teacher teacher) {
        dao.delete(teacher);
    }

    public List<Teacher> findAllTeachers() {
        return dao.findAllTeachers();
    }

    public List<Teacher> findTeacherByName(String firstName, String lastName) {
        return dao.findTeacherByName(firstName, lastName);
    }

    public List<Teacher> findTeacherByLanguage(Language language) {
        return dao.findTeacherByLanguage(language);
    }
}