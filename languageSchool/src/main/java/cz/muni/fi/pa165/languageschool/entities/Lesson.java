package cz.muni.fi.pa165.languageschool.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author xchrastk
 */
@Entity
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Temporal(TemporalType.TIME)
    private Date time;
        
    @ManyToOne
    private Course course;
    
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Student> students = new ArrayList<Student>();
    
    @ManyToOne
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       if (!(object instanceof Lesson)) {
            return false;
        }
        Lesson other = (Lesson) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", date=" + date + ", time=" + time + ", course=" + course + ", students=" + students + ", teacher=" + teacher + '}';
    }       
}
