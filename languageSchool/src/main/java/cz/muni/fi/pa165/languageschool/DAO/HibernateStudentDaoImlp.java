package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.api.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.entities.Student;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author
 */
@Repository
public class HibernateStudentDaoImlp implements StudentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Student create(Student student) {
		sessionFactory.getCurrentSession().persist(student);
		return student;
	}

	@Override
	public Student read(long id) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, id);
	}

	@Override
	public Student update(Student student) {
		sessionFactory.getCurrentSession().update(student);
		return student;
	}

	@Override
	public Student delete(Student student) {
		sessionFactory.getCurrentSession().delete(student);
		return student;
	}
	
	@Override
	public List<Student> findAllStudents() {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT s FROM Student s")
				             .list();
	}
    
	@Override
    public Student findStudentByEmail(String email) {
        return (Student) sessionFactory.getCurrentSession()
				             .createQuery("SELECT s FROM Student s WHERE s.email = :email")
							 .setParameter("email", email)
				             .uniqueResult();
    }

	@Override
	public List<Student> findStudentByName(String firstName, String lastName) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.lastName = :lastName")
							 .setParameter("firstName", firstName)
				             .setParameter("lastName", lastName)
				             .list();
	}

	@Override
	public List<Student> findStudentByLesson(Lesson lesson) {
		return sessionFactory.getCurrentSession()
				             .createQuery("SELECT l.students FROM Lesson l WHERE l.id=:id")
							 .setParameter("id", lesson.getId())
				             .list();
	}
}
