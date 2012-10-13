package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.DAO.StudentDAO;
import cz.muni.fi.pa165.languageschool.DAO.StudentDAOImpl;
import cz.muni.fi.pa165.languageschool.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		
		
		Student student = new Student();
		student.setFirstName("Martin");
		student.setLastName("Novak");
		
		StudentDAO students = new StudentDAOImpl();
		students.create(student);
		
    }
}
