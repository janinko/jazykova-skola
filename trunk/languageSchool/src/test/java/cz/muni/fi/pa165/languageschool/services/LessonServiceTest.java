/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbrazdil
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = true)
public class LessonServiceTest {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private StudentDAO studentDao;
	
	
	
    @Before
    public void setUp() {
    }

    @Test
    public void test1() {		
		Lesson l1 = new Lesson();
		
		lessonService.addLesson(l1);
		Lesson l2 = lessonService.getAllLessons().iterator().next();
		
		System.out.println("Lesson l1" + l1 + " ~ " + l1.getId());
		System.out.println("Lesson l2" + l2 + " ~ " + l2.getId());
		
	}
	
	/*@Test
    public void test1() {
		Student s1 = studentDao.create(new Student("Franta", "Novák"));
		Student s2 = studentDao.create(new Student("Pepa", "Novotný"));
		
		Lesson l1 = new Lesson();
		l1.getStudents().add(s1);
		
		lessonService.addLesson(l1);
		Lesson l2 = lessonService.getAllLessons().iterator().next();
		
		System.out.println("Lesson l1" + l1 + " ~ " + l1.getStudents());
		System.out.println("Lesson l2" + l2 + " ~ " + l2.getStudents());
		
	}*/
}
