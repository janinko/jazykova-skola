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
 * @author
 */
@Repository
public class HibernateLessonDaoImlp implements LessonDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Lesson create(Lesson lesson) {
		sessionFactory.getCurrentSession().persist(lesson);
		return lesson;
	}

	@Override
	public Lesson read(long id) {
		return (Lesson) sessionFactory.getCurrentSession().get(Lesson.class, id);
	}

	@Override
	public Lesson update(Lesson lesson) {
		sessionFactory.getCurrentSession().update(lesson);
		return lesson;
	}

	@Override
	public Lesson delete(Lesson lesson) {
		sessionFactory.getCurrentSession().delete(lesson);
		return lesson;
	}
	
	@Override
	public List<Lesson> findAllLessons() {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT l FROM Lesson l")
				             .list();
	}

	@Override
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
