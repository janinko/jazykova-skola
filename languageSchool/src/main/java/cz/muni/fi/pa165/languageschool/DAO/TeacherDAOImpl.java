package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author xchrastk
 */
public class TeacherDAOImpl implements TeacherDAO {

    private EntityManagerFactory emf;

    public TeacherDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Teacher create(Teacher teacher) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        Teacher t = em.find(Teacher.class, teacher.getId());
        em.close();

        return t;
    }

    public Teacher read(long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Teacher.class, id);
    }

    public Teacher update(Teacher teacher) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Teacher t = em.merge(teacher);
        em.persist(t);
        em.getTransaction().commit();
        em.close();

        return t;
    }

    public Teacher delete(Teacher teacher) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Teacher t = em.find(Teacher.class, teacher.getId());
        em.remove(t);
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
                + "t.firstName = :firstName AND t.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
    
    public Teacher findTeacherByEmail(String email) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT t FROM Teacher t WHERE "
                + "t.email = :email");
        query.setParameter("email", email);
        return (Teacher) query.getSingleResult();
    }
}
