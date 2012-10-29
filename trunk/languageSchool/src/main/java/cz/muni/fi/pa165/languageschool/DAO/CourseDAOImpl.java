package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author xchrastk
 */
@Repository
public class CourseDAOImpl implements CourseDAO {

	@PersistenceContext
    private EntityManager em;

	
    public Course create(Course course) {
        em.persist(course);
		
		return course;
    }

    public Course read(long id) {
        return em.find(Course.class, id);
    }

    public void update(Course course) {
        em.merge(course);
    }

    public void delete(Course course) {
        em.remove(em.find(Course.class, course.getId()));
    }

    public List<Course> findAllCourses() {
        Query query = em.createQuery("SELECT c FROM Course c");
        return query.getResultList();
    }

    public List<Course> findCourseByLanguage(Language language) {
        Query query = em.createQuery("SELECT c FROM Course c "
                + "WHERE c.language = :language");
        query.setParameter("language", language);

        return query.getResultList();
    }
}
