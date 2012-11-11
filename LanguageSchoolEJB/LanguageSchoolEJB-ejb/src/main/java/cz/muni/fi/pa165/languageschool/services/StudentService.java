package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.StudentDAOLocal;
import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

/**
 *
 * @author xchrastk
 */
public class StudentService implements StudentServiceLocal {
    @Resource
    private SessionContext ctx;
    
    @EJB
    private StudentDAOLocal studentDao;

    @Override
    public void createStudent(Student student) {
        studentDao.create(student);
    }
    
    @Override
    public void readStudent(Student student) {
        studentDao.read(student.getId());
    }
}
