package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbrazdil
 */
@Repository
public class HibernateLessonDaoImlp implements LessonDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Lesson create(Lesson lesson) {
		sessionFactory.getCurrentSession().persist(lesson);
		return lesson;
	}

	public Lesson read(long id) {
		return (Lesson) sessionFactory.getCurrentSession().get(Lesson.class, id);
	}

	public Lesson update(Lesson lesson) {
		sessionFactory.getCurrentSession().update(lesson);
		return lesson;
	}

	public Lesson delete(Lesson lesson) {
		sessionFactory.getCurrentSession().delete(lesson);
		return lesson;
	}
	
	public List<Lesson> findAllLessons() {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT l FROM Lesson l")
				             .list();
	}

	public List<Lesson> findLessonByCourse(Course course) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT l FROM Lesson l WHERE l.course = :course")
							 .setParameter("course", course)
				             .list();
	}
}
