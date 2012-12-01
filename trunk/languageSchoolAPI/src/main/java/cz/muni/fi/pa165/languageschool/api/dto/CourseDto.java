package cz.muni.fi.pa165.languageschool.api.dto;

import java.io.Serializable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class CourseDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String language;
	private int level;

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

	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CourseDto)) {
            return false;
        }
        CourseDto other = (CourseDto) object;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "CourseDto{" + "id=" + id + ", name=" + name + ", language=" + language + ", level=" + level + '}';
	}
}
