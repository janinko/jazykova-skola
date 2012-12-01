package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonForm;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class CoursePage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private CourseDtoAdapter courses;
    @SpringBean
    private LessonDtoAdapter lessons;
    
    public CoursePage(PageParameters parameters) {
		super(parameters);
		Long courseid = parameters.get("courseid").toLong(-1);
		
		if(courseid == -1){
			add(new Label("courseName", new ResourceModel("MusisVybratKurz.")));
			return;
		}

		CourseDto course = courses.read(courseid);
		
		if(course == null){
			add(new Label("courseName", new ResourceModel("KurzNeexistuje!")));
			return;
		}

		Label header = new Label("courseName", course.getName());
		LessonList lessonList = new LessonList("lessonList",lessons.getLessonsByCourse(course),false);

		add(new LessonForm("addLessonForm", course));
		add(header);
		add(lessonList);
    }
}
