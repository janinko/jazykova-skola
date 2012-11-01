package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbrazdil
 */
@Repository
public class HibernateTeacherDaoImlp implements TeacherDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Teacher create(Teacher teacher) {
		sessionFactory.getCurrentSession().persist(teacher);
		return teacher;
	}

	public Teacher read(long id) {
		return (Teacher) sessionFactory.getCurrentSession().get(Teacher.class, id);
	}

	public Teacher update(Teacher teacher) {
		sessionFactory.getCurrentSession().update(teacher);
		return teacher;
	}

	public Teacher delete(Teacher teacher) {
		sessionFactory.getCurrentSession().delete(teacher);
		return teacher;
	}
	
	public List<Teacher> findAllTeachers() {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT t FROM Teacher t")
				             .list();
	}

	public List<Teacher> findTeacherByName(String firstName, String lastName) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT t FROM Teacher t WHERE t.firstName = :firstName AND t.lastName = :lastName")
							 .setParameter("firstName", firstName)
				             .setParameter("lastName", lastName)
				             .list();
	}
}
