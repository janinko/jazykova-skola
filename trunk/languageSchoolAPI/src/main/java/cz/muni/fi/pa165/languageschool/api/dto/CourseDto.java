package cz.muni.fi.pa165.languageschool.api.dto;

/**
 *
 * @author fivekeyem
 */
public class CourseDto {

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
}
