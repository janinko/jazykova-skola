package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbrazdil
 */
@Repository
public class HibernateCourseDaoImlp implements HibernateCourseDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public Course create(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(course);
		return course;
	}

	public Course read(long id) {
		Session session = sessionFactory.getCurrentSession();
		Course course = (Course) session.get(Course.class, id);
		return course;
	}

	public Course update(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.update(course);
		return course;
	}

	public Course delete(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
		return course;
	}

	public List<Course> findAllCourses() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Course> courses = session.createQuery("SELECT c FROM Course c").list();
		return courses;
	}

	public List<Course> findCourseByLanguage(Language language) {
		
		Session session = sessionFactory.getCurrentSession();
		List<Course> courses = session
								.createQuery("SELECT c FROM Course c WHERE c.language = :language")
								.setParameter("language", language)
								.list();
		return courses;
	}
}
