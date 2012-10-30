/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author jbrazdil
 */
public class HibernateTemplateCourseDaoImlp implements CourseDAO {
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public Course create(Course course) {
		hibernateTemplate.persist(course);
		return course;
	}

	public Course read(long id) {
		return hibernateTemplate.get(Course.class, id);
	}

	public void update(Course course) {
		hibernateTemplate.merge(course);
	}

	public void delete(Course course) {
		hibernateTemplate.delete(course);
		hibernateTemplate.flush();
	}

	public List<Course> findAllCourses() {
		return hibernateTemplate.find("SELECT c FROM Course c");
	}

	public List<Course> findCourseByLanguage(Language language) {
		return hibernateTemplate.find("SELECT c FROM Course c WHERE c.language = ?", language);
	}
}
