package cz.muni.fi.pa165.languageschool.api.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class TeacherDto implements Serializable{
	private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> languages;
    private String nativeLanguage;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TeacherDto)) {
            return false;
        }
        TeacherDto other = (TeacherDto) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "TeacherDto{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", languages=" + languages + ", nativeLanguage=" + nativeLanguage + '}';
	}
}
