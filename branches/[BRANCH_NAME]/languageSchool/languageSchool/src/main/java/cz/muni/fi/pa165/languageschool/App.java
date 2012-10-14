package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.DAO.CourseDAO;
import cz.muni.fi.pa165.languageschool.DAO.CourseDAOImpl;
import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );		
		
		Course course = new Course();
                course.setLanguage(Teacher.Language.RU);
                System.out.println(course.getLanguage());
                CourseDAO dao = new CourseDAOImpl();
		dao.create(course);
                System.out.println(dao.read(course.getId()).getLanguage());
                
    }
}
