package cz.muni.fi.pa165.languageschool;

import cz.muni.fi.pa165.languageschool.api.entities.Lesson;
import cz.muni.fi.pa165.languageschool.api.services.LessonService;
import java.util.Set;
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
		Logger.getRootLogger().setLevel(Level.ALL);
        System.out.println( "Hello World!" );
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		LessonService lessonService = ctx.getBean(LessonService.class);
		
		Set<Lesson> lessons = lessonService.getAllLessons();
		
		System.out.println( lessons);
		
        System.out.println( "Goodby World!" );
    }
}
