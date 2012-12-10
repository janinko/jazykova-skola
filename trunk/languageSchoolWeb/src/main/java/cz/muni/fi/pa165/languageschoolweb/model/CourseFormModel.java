package cz.muni.fi.pa165.languageschoolweb.model;

import cz.muni.fi.pa165.languageschool.api.Language;
import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class CourseFormModel implements IClusterable {
	private static final long serialVersionUID = 1L;
	
	Integer courseLevel;
	String courseName;
	Language courseLanguage;

	public Integer getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(Integer courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Language getCourseLanguage() {
		return courseLanguage;
	}

	public void setCourseLanguage(Language courseLanguage) {
		this.courseLanguage = courseLanguage;
	}

	@Override
	public String toString() {
		return "CourseFormModel{" + "courseLevel=" + courseLevel + ", courseName=" + courseName + ", courseLanguage=" + courseLanguage + '}';
	}
	
}
