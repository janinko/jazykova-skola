package cz.muni.fi.pa165.languageschool.api.dto;

import java.io.Serializable;

/**
 *
 * @author fivekeym
 */
public class StudentDto implements Serializable{
	
	private Long id;
	private String firstName;
	private String lastName;
    private String email;
	private int age;

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
    
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

    @Override
    public String toString() {
        return "StudentDto{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + '}';
    }    
}
