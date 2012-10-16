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
    private EntityManagerFactory emf;
    
    public LectionDAOImpl(EntityManagerFactory emf) {
         this.emf = emf;
    }   
    
    public Lection create(Lection lection) {
        EntityManager em = emf.createEntityManager();
		Lection l;
        
        em.getTransaction().begin();
        em.persist(lection);
        em.getTransaction().commit();
		l = em.find(Lection.class, lection.getId());
        em.close();
        
        return l;
    }

    public Lection read(long id) {
        EntityManager em = emf.createEntityManager();
        
        return em.find(Lection.class, id);
    }

    public Lection update(Lection lection) {
        EntityManager em = emf.createEntityManager();       
        
        em.getTransaction().begin();
		Lection l = em.merge(lection);
        em.persist(l);
        em.getTransaction().commit();
        em.close();
        
        return l;
    }

    public Lection delete(Lection lection) {
        EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
		Lection l = em.find(Lection.class, lection.getId());
        em.remove(l);
        em.getTransaction().commit();
        em.close();
        
        return lection;
    }

    public List<Lection> findAllLections() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lection l");
        return query.getResultList();  
    }    
    
    
    public List<Lection> findLectionByCourse(Course course) {        
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lection l WHERE "
            +"l.course = :course");
        query.setParameter("course", course);
        return query.getResultList();
    } 
}
