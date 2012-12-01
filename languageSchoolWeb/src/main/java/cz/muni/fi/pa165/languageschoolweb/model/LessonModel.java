package cz.muni.fi.pa165.languageschoolweb.model;

import java.util.Date;
import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class LessonModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	private String teacherEmail;
	private Date date = new Date();

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
