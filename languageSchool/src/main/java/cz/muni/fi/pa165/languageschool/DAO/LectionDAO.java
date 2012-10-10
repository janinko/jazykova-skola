package cz.muni.fi.pa165.languageschool.DAO;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Lection;
import java.util.List;

/**
 *
 * @author xchrastk
 */
public interface LectionDAO {
    public Lection create(Lection lection);
    public Lection read(long id);
    public Lection update(Lection lection);
    public Lection delete(Lection lection);
    
    public List<Lection> findAllLections();
    public List<Lection> findLectionByCourse(Course course);
}
