package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class TeacherPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private TeacherDtoAdapter teachers;
    
    public TeacherPage(PageParameters parameters) {
		super(parameters);

		if(parameters == null || parameters.get("email") == null){
			getSession().error(getString("MusisVybratUcitele."));
			throw new RestartResponseException(ErrorPage.class);
		}
		String email = parameters.get("email").toString();
		TeacherDto teacher = teachers.readTeacher(email);
		
		if(teacher == null){
			getSession().error(getString("UcitelNeexistuje!"));
			throw new RestartResponseException(ErrorPage.class);
		}

		Label header = new Label("teacherName", teacher.getFirstName() + " " +teacher.getLastName());
		Label emailLabel = new Label("teacherEmail", teacher.getEmail());
		StringBuilder languages = new StringBuilder();
		for(Language l : teacher.getLanguages()){
			languages.append(l);
			languages.append(", ");
		}
		languages.delete(languages.length()-2, languages.length());
		Label languagesLabel = new Label("teacherLangs", languages.toString());
		Label nativeLabel;
		if(teacher.getNativeLanguage() != null){
			nativeLabel = new Label("teacherNative", teacher.getNativeLanguage().toString());
		}else{
			nativeLabel = new Label("teacherNative");
			nativeLabel.setVisible(false);
		}

		LessonList lessonList = new LessonList("lessonList",teachers.getTeachersLessons(teacher),false,"");

		add(header);
		add(emailLabel);
		add(languagesLabel);
		add(nativeLabel);
		add(lessonList);
    }
}
