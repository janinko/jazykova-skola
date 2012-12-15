package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.components.StudentList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class LessonPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private LessonDtoAdapter lessons;
    
    public LessonPage(PageParameters parameters) {
		super(parameters);

		if(parameters == null || parameters.get("id") == null){
			getSession().error(getString("MusisVybratLekci."));
			throw new RestartResponseException(ErrorPage.class);
		}
		Long id = parameters.get("id").toLong();
		LessonDto lesson = lessons.read(id);
		
		if(lesson == null){
			getSession().error(getString("LekceNeexistuje!"));
			throw new RestartResponseException(ErrorPage.class);
		}

		CourseDto course = lesson.getCourse();

        PageParameters params = new PageParameters();
        params.set("email",lesson.getTeacherEmail());
		Link lessonTeacherLink = new BookmarkablePageLink("lessonTeacherLink", TeacherPage.class, params);

		DateFormat dateFormat = new SimpleDateFormat("dd. MM. YYYY");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Label lessonName    = new Label("lessonName", course.getName());
		Label lessonLang    = new Label("lessonLang", course.getLanguage().toString());
		Label lessonTeacher = new Label("lessonTeacher", lesson.getTeacherName());
		Label lessonDate    = new Label("lessonDate", dateFormat.format(lesson.getDate().getTime()));
		Label lessonTime    = new Label("lessonTime", timeFormat.format(lesson.getDate().getTime()));

		StudentList studentList = new StudentList("studentList",lessons.findStudentsByLesson(lesson));

		add(lessonName);
		add(lessonLang);
		add(lessonDate);
		add(lessonTime);
		add(studentList);
		add(lessonTeacherLink);
		lessonTeacherLink.add(lessonTeacher);
    }
}
