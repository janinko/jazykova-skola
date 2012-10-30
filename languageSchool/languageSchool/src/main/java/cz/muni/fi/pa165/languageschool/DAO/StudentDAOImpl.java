package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class StudentDAOImpl implements StudentDAO {

    private EntityManagerFactory emf;

    public StudentDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

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
        Student s = em.merge(student);
        em.persist(s);
        em.getTransaction().commit();
        em.close();

        return s;
    }

    public Student delete(Student student) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Student s = em.find(Student.class, student.getId());
        em.remove(s);
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
                + "s.firstName = :firstName AND s.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    public List<Student> findStudentByLesson(Lesson lesson) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT l.students FROM Lesson l WHERE l.id=:id");
        query.setParameter("id", lesson.getId());

        return query.getResultList();
    }
}
