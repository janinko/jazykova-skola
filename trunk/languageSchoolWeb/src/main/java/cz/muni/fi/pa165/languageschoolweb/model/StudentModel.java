/*
 * Copyright 2012 jbrazdil.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.muni.fi.pa165.languageschoolweb.model;

import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import org.apache.wicket.util.io.IClusterable;

/**
 *
 * @author jbrazdil
 */
public class StudentModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	Integer age;
	String firstName;
	String lastName;
	String newPassword;
	String newPasswordRepeat;

	StudentDto student;

	public StudentModel(){}

	public StudentModel(StudentDto student){
		firstName = student.getFirstName();
		lastName = student.getLastName();
		age = student.getAge();
		this.student = student;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public StudentDto getStudent() {
		return student;
	}
}
