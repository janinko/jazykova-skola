/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.entities.Course;
import cz.muni.fi.pa165.languageschool.entities.Teacher;

/**
 *
 * @author fivekeyem
 */
public class CourseDto {

	private String id;
	private String name;
	private String language;
	private int level;
	
	
	public CourseDto(Course course) {
		this.id = course.getId().toString();
		this.name = course.getName();
		this.language = course.getLanguage().toString();
		this.level = course.getLevel();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	public Course adaptToEntity() {
		Course c = new Course();
		c.setId(Long.parseLong(getId()));
		c.setName(getName());
		c.setLanguage(Teacher.Language.valueOf(getLanguage().toString()));
		c.setLevel(getLevel());
		
		return c;
	}
		
}
