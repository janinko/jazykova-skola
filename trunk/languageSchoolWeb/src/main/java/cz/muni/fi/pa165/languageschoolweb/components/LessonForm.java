package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.model.LessonModel;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public final class LessonForm extends Panel {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private CourseDtoAdapter courses;
	@SpringBean
	private TeacherDtoAdapter teachers;

	CourseDto course;
	

	public LessonForm(String id, CourseDto course) {
		super(id);

		this.course = course;

		add(new InputForm("form"));
	}
	
	class InputForm extends Form<LessonModel>{
		private static final long serialVersionUID = 1L;
		
		private String selectedLanguage;
		
		public InputForm(String id) {
			super(id, new CompoundPropertyModel<LessonModel>(new LessonModel()));
			
			add(new TextField<String>("teacherEmail", String.class)
					.setRequired(true)
			);

			DateTimeField dtf = new DateTimeField("date");
			dtf.setRequired(true);
			add(dtf);

			add(new Button("addButton"));
		}

		public String getSelectedLanguage() {
			return selectedLanguage;
		}

		public void setSelectedLanguage(String selectedLanguage) {
			this.selectedLanguage = selectedLanguage;
		}
	
		@Override
		public void onSubmit()
        {
			LessonModel model = (LessonModel) getDefaultModelObject();

			LessonDto lesson = new LessonDto();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(model.getDate());

			lesson.setDate(calendar);
			lesson.setTeacherEmail(model.getTeacherEmail());

			try{
				courses.addLessonToCourse(course, lesson);
				getSession().info(getString("lessonCreated"));
				setResponsePage(getPage().getClass(),getPage().getPageParameters());
			}catch(Exception ex){
				getSession().error(getString("lessonNotCreated"));
				getSession().error(ex);
				setResponsePage(getPage());
			}
        }

	}

	static class DataModel implements IModel<Date>{
		private static final long serialVersionUID = 1L;
		Date date;

		@Override
		public Date getObject() {
			return date;
		}

		@Override
		public void setObject(Date t) {
			date = t;
		}

		@Override
		public void detach() {
			date=null;
		}

	}
}
