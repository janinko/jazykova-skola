package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class LectionDAOImpl implements LectionDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
    
    public Lection create(Lection course) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Lection.class, course.getId());
    }

    public Lection read(long id) {
        EntityManager em = emf.createEntityManager();
        
        return em.find(Lection.class, id);
    }

    public Lection update(Lection course) {
        EntityManager em = emf.createEntityManager();       
        
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Lection.class, course.getId());
    }

    public Lection delete(Lection course) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();
        em.close();
        
        return course;
    }

    public List<Lection> findAllLections() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lection l");
        return query.getResultList();  
    }    
    
    
    public List<Lection> findLectionByCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
        /*EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lection l WHERE "
            +"l.course LIKE :course");
        query.setParameter("course", course);
        return query.getResultList();*/
    } 
}
