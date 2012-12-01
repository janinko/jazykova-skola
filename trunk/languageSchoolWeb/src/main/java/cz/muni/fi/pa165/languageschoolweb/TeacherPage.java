/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class TeacherPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private TeacherDtoAdapter teachers;
    
    public TeacherPage(PageParameters parameters) {
		super(parameters);
		String email = parameters.get("email").toString();

		if(email == null){
			add(new Label("teacherName", new ResourceModel("MusisVybratUcitele.")));
			return;
		}

		TeacherDto teacher = teachers.readTeacher(email);
		
		if(teacher == null){
			add(new Label("teacherName", new ResourceModel("UcitelNeexistuje!")));
			return;
		}

		Label header = new Label("teacherName", teacher.getFirstName() + " " +teacher.getLastName());
		LessonList lessonList = new LessonList("lessonList",teachers.getTeachersLessons(teacher),false);

		add(header);
		add(lessonList);
    }
}
