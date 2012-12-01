package cz.muni.fi.pa165.languageschoolweb.model;

import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
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
