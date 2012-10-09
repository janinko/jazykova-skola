/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author xchrastk
 */
public class CourseDAOImpl implements CourseDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
    
    public Course create(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Course.class, course.getId());
    }

    public Course read(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course update(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Course delete(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<Course> findAllCourses() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
