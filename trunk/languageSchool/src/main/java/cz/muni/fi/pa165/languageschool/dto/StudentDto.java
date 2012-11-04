/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.entities.Student;

/**
 *
 * @author fivekeym
 */
public class StudentDto {
	
	private String id;
	private String firstName;
	private String lastName;
	private int age;

	public StudentDto(Student s) {
		this.id = s.getId().toString();
		this.firstName = s.getFirstName();
		this.lastName = s.getLastName();
		this.age = s.getAge();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	public Student adaptToEntity() {
		Student s = new Student();
		s.setId(Long.parseLong(getId()));
		s.setFirstName(getFirstName());
		s.setLastName(getLastName());
		s.setAge(getAge());
		
		return s;
	}
	
}
