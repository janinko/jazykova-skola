package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Lection;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class StudentDAOImpl implements StudentDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsPU");
    
    public Student create(Student student) {
        EntityManager em = emf.createEntityManager();
		Student s;
        
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
		s = em.find(Student.class, student.getId());
        em.close();
        
        return s;
    }

    public Student read(long id) {
        EntityManager em = emf.createEntityManager();
        
        return em.find(Student.class, id);
    }

    public Student update(Student student) {
        EntityManager em = emf.createEntityManager();             
        
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        
        return em.find(Student.class, student.getId());
    }

    public Student delete(Student student) {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
        
        return student;
    }

    public List<Student> findAllStudents() {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT s FROM Student s");
        return query.getResultList();  
    }    

    public List<Student> findStudentByName(String firstName, String lastName) {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT s FROM Student s WHERE "
            +"s.firstName LIKE :fistName AND s.lastName LIKE :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        
        return query.getResultList();
    }    
    
    public List<Student> findStudentByLection(Lection lection) {
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT l.students FROM Lection l WHERE l.id=:id");
        query.setParameter("id", lection.getId());
        
        return (List<Student>)query.getSingleResult();
    } 
   
}
