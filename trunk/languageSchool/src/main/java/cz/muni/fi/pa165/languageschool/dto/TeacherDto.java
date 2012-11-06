/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.languageschool.dto;

import cz.muni.fi.pa165.languageschool.entities.Teacher;
import cz.muni.fi.pa165.languageschool.entities.Teacher.Language;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xchrastk
 */
public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> languages;
    private String nativeLanguage;
	
	public TeacherDto(){
		
	}

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.email = teacher.getEmail();
        this.nativeLanguage = teacher.getNativeLanguage().toString();
        this.languages = new HashSet<String>();
        for (Language l : teacher.getLanguages()) {
            languages.add(l.toString());
        }
    }

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

    
    public Teacher adaptToEntity() {
        Teacher t = new Teacher();
        t.setId(id);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        t.setEmail(email);
        t.setNativeLanguage(Language.valueOf(nativeLanguage));
        Set<Language> lang = new HashSet<Language>();
        for (String l : languages) {
            lang.add(Language.valueOf(l));
        }
        t.setLanguages(lang);
        return t;
    }
}
