package cz.muni.fi.pa165.languageschool.services;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lesson;

/**
 *
 * @author xchrastk
 */
public interface CourseService {
    /*
     * @return updated lection
     */
    Course addLection(Lesson lesson);
    
    //+ CRUD operace
}
