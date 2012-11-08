package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author xchrastk
 */
@Repository
public class HibernateTeacherDaoImlp implements HibernateTeacherDao {

	private SessionFactory sessionFactory;
    
    @Autowired
    @Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
    @Override
	public Teacher create(Teacher teacher) {
		sessionFactory.getCurrentSession().persist(teacher);
		return teacher;
	}

    @Override
	public Teacher read(long id) {
		return (Teacher) sessionFactory.getCurrentSession().get(Teacher.class, id);
	}

    @Override
	public Teacher update(Teacher teacher) {
		sessionFactory.getCurrentSession().update(teacher);
		return teacher;
	}

    @Override
	public Teacher delete(Teacher teacher) {
		sessionFactory.getCurrentSession().delete(teacher);
		return teacher;
	}
	
    @Override
	public List<Teacher> findAllTeachers() {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT t FROM Teacher t")
				             .list();
	}

    @Override
	public List<Teacher> findTeacherByName(String firstName, String lastName) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT t FROM Teacher t WHERE t.firstName = :firstName AND t.lastName = :lastName")
							 .setParameter("firstName", firstName)
				             .setParameter("lastName", lastName)
				             .list();
	}

    @Override
    public Teacher findTeacherByEmail(String email) {
        return (Teacher) sessionFactory.getCurrentSession()
				             .createQuery("SELECT t FROM Teacher t WHERE t.email = :email")
							 .setParameter("email", email)
				             .list().get(0);
    }
    
}
