package cz.muni.fi.pa165.languageschoolweb.model;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import java.util.Set;
import org.apache.wicket.util.io.IClusterable;

/**
 *
 * @author jbrazdil
 */
public class TeacherModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	Integer age;
	String firstName;
	String lastName;
	String newPassword;
	String newPasswordRepeat;    
    Set<Language> languages;

	TeacherDto teacher;

	public TeacherModel(){}

	public TeacherModel(TeacherDto teacher){
		firstName = teacher.getFirstName();
		lastName = teacher.getLastName();
		languages = teacher.getLanguages();
		this.teacher = teacher;
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

	public TeacherDto getTeacher() {
		return teacher;
	}

    public Set<Language> getLanguages() {
        return languages;
    }

    public void Languages(Set<Language> languages) {
        this.languages = languages;
    }
}
