package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.services.CourseService;
import cz.muni.fi.pa165.languageschool.services.LessonService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {		
        System.out.println( "Hello World!" );
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		CourseService courseService = ctx.getBean(CourseService.class);
		courseService.createCourse(new Course("Anglictinaa"));
		
		
        System.out.println( "Goodby World!" );
    }
}
