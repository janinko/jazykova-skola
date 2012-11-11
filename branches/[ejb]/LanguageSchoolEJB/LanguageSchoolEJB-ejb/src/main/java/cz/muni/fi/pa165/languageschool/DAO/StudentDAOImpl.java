package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author xchrastk
 */
@Stateless(mappedName="StudentDAOImpl")
public class StudentDAOImpl implements StudentDAOLocal {

	@PersistenceContext(unitName = "lsPU")
    private EntityManager em;


	@Override
    public Student create(Student student) {
        Student s;

        em.persist(student);
        s = em.find(Student.class, student.getId());

        return s;
    }

	@Override
    public Student read(long id) {
        return em.find(Student.class, id);
    }

  
}
