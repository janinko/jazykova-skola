/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author jbrazdil
 */
public class LessonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Long id;
	Calendar date;
	CourseDto course;
	String teacherName;
	String teacherEmail;

	/**
	 * Create new lesson transfer object from lesson entity for sending data to frontend.
	 * @param date Date and time of lesson start.
	 * @param teacherEmail Email of teacher who is teaching this lesson.
	 */
	public LessonDto(Lesson lesson) {
		id = lesson.getId();
		
		date = new GregorianCalendar();
		date.setTime(lesson.getDate());
		Calendar tcal = Calendar.getInstance();
		tcal.setTime(lesson.getTime());
		date.set(Calendar.HOUR_OF_DAY, tcal.get(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, tcal.get(Calendar.MINUTE));
		date.set(Calendar.SECOND, tcal.get(Calendar.SECOND));
		date.set(Calendar.MILLISECOND, tcal.get(Calendar.MILLISECOND));
		
		course = new CourseDto(lesson.getCourse());
		
		teacherName = lesson.getTeacher().getFirstName() + " " + lesson.getTeacher().getLastName();
		teacherEmail = lesson.getTeacher().getEmail();
	}
	
	/**
	 * Create new lesson transfer object for sending data to backend.
	 * @param date Date and time of lesson start.
	 * @param teacherEmail Email of teacher who is teaching this lesson.
	 */
	public LessonDto(Calendar date, String teacherEmail){
		this.id = null;
		this.date = (Calendar) date.clone();
		this.course = null;
		this.teacherName = null;
		this.teacherEmail = teacherEmail;
	}

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
	
	public Lesson adaptToEntity() {
		Lesson l = new Lesson();
		throw new UnsupportedOperationException("StudentTO is not yet implemented");
		
	}
}
