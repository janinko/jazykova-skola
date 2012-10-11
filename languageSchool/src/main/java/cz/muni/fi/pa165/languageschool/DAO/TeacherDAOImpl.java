package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class TeacherDAOImpl implements TeacherDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
    
    public Teacher create(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Teacher.class, teacher.getId());
    }

    public Teacher read(long id) {
        EntityManager em = emf.createEntityManager();
        
        return em.find(Teacher.class, id);
    }

    public Teacher update(Teacher teacher) {
        EntityManager em = emf.createEntityManager();       
        
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Teacher.class, teacher.getId());
    }

    public Teacher delete(Teacher teacher) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(teacher);
        em.getTransaction().commit();
        em.close();
        
        return teacher;
    }

    public List<Teacher> findAllTeachers() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT t FROM Teacher t");
        return query.getResultList();  
    }    

    public List<Teacher> findTeacherByName(String firstName, String lastName) {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT t FROM Teacher t WHERE "
            +"t.firstName LIKE :fistName AND t.lastName LIKE :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }   

    public List<Teacher> findTeacherByLanguage(Language language) {
        EntityManager em = emf.createEntityManager();
        
        Language[] languageArray = Language.values();
        Collection<String> languages = new ArrayList<String>();
        for(Language lang: languageArray) {
            languages.add(lang.toString());
        }
        
        Query query = em.createQuery("SELECT t FROM Teacher t WHERE t.languages IN {:languages}");        
        query.setParameter("languages", languages);
        
        return query.getResultList();
    }    
}
