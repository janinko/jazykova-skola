package cz.muni.fi.pa165.languageschoolweb.model;

import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class GeneratorFormModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	Integer teacherCount = 10;
	Integer courseCount = 5;
	Integer lessonCount = 25;
	Integer studentCount = 30;

	public Integer getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(Integer teacherCount) {
		this.teacherCount = teacherCount;
	}

	public Integer getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(Integer courseCount) {
		this.courseCount = courseCount;
	}

	public Integer getLessonCount() {
		return lessonCount;
	}

	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	@Override
	public String toString() {
		return "GeneratorFormModel{" + "teacherCount=" + teacherCount + ", courseCount=" + courseCount + ", lessonCount=" + lessonCount + ", studentCount=" + studentCount + '}';
	}
}