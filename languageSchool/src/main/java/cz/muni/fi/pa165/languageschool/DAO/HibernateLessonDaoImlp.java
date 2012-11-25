package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.api.DAO.LessonDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Course;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import java.sql.Date;
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
	
	private SessionFactory sessionFactory;

	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
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

	@Override
	public List<Lesson> findUpcomingLessons(Date date) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT l FROM Lesson l WHERE l.lessonDate BETWEEN :startDate AND :endDate")
							 .setParameter("startDate", new java.util.Date())
							 .setParameter("endDate", date)
				             .list();
	}
}
