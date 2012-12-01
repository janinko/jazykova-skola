package cz.muni.fi.pa165.languageschool.api.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author
 */
public class LessonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Long id;
	Calendar date;
	CourseDto course;
	String teacherName;
	String teacherEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
		this.course = course;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       if (!(object instanceof LessonDto)) {
            return false;
        }
        LessonDto other = (LessonDto) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "LessonDto{" + "id=" + id + ", date=" + date + ", course=" + course + ", teacherName=" + teacherName + ", teacherEmail=" + teacherEmail + '}';
	}
}
