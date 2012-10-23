package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class CourseDAOImpl implements CourseDAO {

    private EntityManagerFactory emf;

    public CourseDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Course create(Course course) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        Course c = em.find(Course.class, course.getId());
        em.close();

        return c;
    }

    public Course read(long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Course.class, id);
    }

    public Course update(Course course) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Course c = em.merge(course);
        em.persist(c);
        em.getTransaction().commit();
        em.close();

        return c;
    }

    public Course delete(Course course) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Course c = em.find(Course.class, course.getId());
        em.remove(c);
        em.getTransaction().commit();
        em.close();

        return course;
    }

    public List<Course> findAllCourses() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM Course c");
        return query.getResultList();
    }

    public List<Course> findCourseByLanguage(Language language) {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Course c "
                + "WHERE c.language = :language");
        query.setParameter("language", language);

        return query.getResultList();
    }
}
