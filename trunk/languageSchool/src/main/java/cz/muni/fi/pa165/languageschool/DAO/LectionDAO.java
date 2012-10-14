package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lection;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface LectionDAO {
	
	/**
	 * Save new lection. One course is composed of several lections.
	 * 
	 * @param lection which will be saved
	 * @return saved lection
	 * @throws IllegalArgumentException if lection is null
	 */
    public Lection create(Lection lection);
	
	
	/**
	 * Returns lection by id.
	 *
	 * @param id is unique number of lection.
	 * @return one lection with the id
	 * @throws IllegalArgumentException id is null or is < 1
	 */
    public Lection read(long id);
	
	/**
	 * Change lection which was saved previously.
	 * 
	 * @param lection which will be changed
	 * @return changed lection
	 * @throws IllegalArgumentException if lection is null
	 */
    public Lection update(Lection lection);
	
	/**
	 * Delete of lection
	 * 
	 * @param lection which will be deleted
	 * @return deleted lection
	 * @throws IllegalArgumentException if lection is null
	 */
    public Lection delete(Lection lection);
    
	
	/**
	 * Return all lections.
	 * 
	 * @return List of all lections.
	 */
    public List<Lection> findAllLections();
	
	/**
	 * Return list of lections depending on course.
	 * 
	 * @param course for which lection will be returned
	 * @return list of lections
	 */
    public List<Lection> findLectionByCourse(Course course);
}
