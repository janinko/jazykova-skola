package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xchrastk
 */
@Stateless
public class StudentDAOImpl implements StudentDaoLocal {

	@PersistenceContext
    private EntityManager em;


	@Override
    public Student create(Student student) {
        Student s;

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        s = em.find(Student.class, student.getId());
        em.close();

        return s;
    }

	@Override
    public Student read(long id) {
        return em.find(Student.class, id);
    }

  
}
