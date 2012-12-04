package cz.muni.fi.pa165.languageschool.api.entities;

import cz.muni.fi.pa165.languageschool.api.Language;
import java.io.Serializable;
import javax.persistence.*;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public enum Level {BEGINNER,INTERMEDIATE,ADVANCED}
    
    //@Column(nullable=false, length=60)
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Language language;
    
    @Enumerated(EnumType.ORDINAL)
    private int level;

	
	public Course() {
	}

	public Course(String name) {
			this.name = name;
	}
	

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Course{" + "id=" + id + ", name=" + name + ", language=" + language + ", level=" + level + '}';
	}
}
