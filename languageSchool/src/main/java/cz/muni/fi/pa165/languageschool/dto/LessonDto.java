/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.entities.Lesson;
import cz.muni.fi.pa165.languageschool.entities.Student;
import cz.muni.fi.pa165.languageschool.entities.Teacher;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
		return adaptToEntity(null);
	}
	
	public Lesson adaptToEntity(Teacher teacher) {
		Lesson l = new Lesson();
		if(course != null){
			l.setCourse(course.adaptToEntity());
		}
		if(date != null){
			Calendar day = (Calendar) date.clone();
			day.set(Calendar.HOUR_OF_DAY, 0);
			day.set(Calendar.MINUTE, 0);
			day.set(Calendar.SECOND, 0);
			day.set(Calendar.MILLISECOND, 0);
			l.setDate(new Date(day.getTimeInMillis()));

			l.setDate(new Date(date.getTimeInMillis() - day.getTimeInMillis()));
		}
		l.setId(id);
		l.setTeacher(teacher);
		
		
		// studends are not setted, when lesson is created, students are epmty,
		// students can themselve enroll and cancel
		// lesson isn't updatable
		
		return l;
	}
}
