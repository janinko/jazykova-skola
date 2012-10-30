package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class LessonDAOImpl implements LessonDAO {
    private EntityManagerFactory emf;
    
    public LessonDAOImpl(EntityManagerFactory emf) {
         this.emf = emf;
    }   
    
    public Lesson create(Lesson lesson) {
        EntityManager em = emf.createEntityManager();
	Lesson l;
        
        em.getTransaction().begin();
        em.persist(lesson);
        em.getTransaction().commit();
	l = em.find(Lesson.class, lesson.getId());
        em.close();
        
        return l;
    }

    public Lesson read(long id) {
        EntityManager em = emf.createEntityManager();
        
        return em.find(Lesson.class, id);
    }

    public Lesson update(Lesson lesson) {
        EntityManager em = emf.createEntityManager();       
        
        em.getTransaction().begin();
		Lesson l = em.merge(lesson);
        em.persist(l);
        em.getTransaction().commit();
        em.close();
        
        return l;
    }

    public Lesson delete(Lesson lesson) {
        EntityManager em = emf.createEntityManager();
                
        em.getTransaction().begin();
		Lesson l = em.find(Lesson.class, lesson.getId());
        em.remove(l);
        em.getTransaction().commit();
        em.close();
        
        return lesson;
    }

    public List<Lesson> findAllLessons() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lesson l");
        return query.getResultList();  
    }    
    
    
    public List<Lesson> findLessonByCourse(Course course) {        
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l FROM Lesson l WHERE "
            +"l.course = :course");
        query.setParameter("course", course);
        return query.getResultList();
    } 
}
