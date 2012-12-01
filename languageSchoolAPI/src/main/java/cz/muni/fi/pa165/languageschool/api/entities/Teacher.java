package cz.muni.fi.pa165.languageschool.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Entity
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Language {AJ,NJ,FJ,RU}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, length=20)
    private String firstName;
    
    @Column(nullable=false, length=30)
    private String lastName;
    
    @Column(nullable=false, length=30, unique=true)
    private String email;
	
    @Column(length=65)
    private String password;
    
	@ElementCollection(targetClass = Language.class, fetch= FetchType.EAGER)
    private Set<Language> languages = new HashSet<Language>();
    
    @Enumerated(EnumType.STRING)
    private Language nativeLanguage;

	public Teacher() {
	}

	public Teacher(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
        this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Language getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(Language nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}

