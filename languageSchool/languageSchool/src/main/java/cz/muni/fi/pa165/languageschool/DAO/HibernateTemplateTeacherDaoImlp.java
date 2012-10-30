
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author xchrastk
 */
public class HibernateTemplateTeacherDaoImlp implements LessonDAO {
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public Lesson create(Lesson lesson) {
		hibernateTemplate.persist(lesson);
		return lesson;
	}

	public Lesson read(long id) {
		return hibernateTemplate.get(Lesson.class, id);
	}

	public Lesson update(Lesson lesson) {
		hibernateTemplate.merge(lesson);
		return lesson;
	}

	public Lesson delete(Lesson lesson) {
		hibernateTemplate.delete(lesson);
		hibernateTemplate.flush();
		return lesson;
	}

	public List<Lesson> findAllLessons() {
		return hibernateTemplate.find("SELECT l FROM Lesson l");
	}

	public List<Lesson> findLessonByCourse(Course course) {
		return hibernateTemplate.find("SELECT l FROM Lesson l WHERE l.course = ?", course);
	}
}
