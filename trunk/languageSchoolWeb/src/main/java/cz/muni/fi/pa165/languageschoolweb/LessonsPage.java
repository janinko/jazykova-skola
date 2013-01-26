package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import java.sql.Date;
import java.util.Set;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class LessonsPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private StudentDtoAdapter students;

    public LessonsPage(PageParameters parameters) {
		super(parameters);
        String act = parameters.get("act").toString();
        Long lessonId = parameters.get("id").toLong(-1);
		Boolean my = false;
        Set<LessonDto> lsns;        
        
        
        if ( (act != null) && act.equals("delete") && (lessonId != null) ) {
			if(parameters.get("my").isNull()){
				lessons.removeLesson(lessons.read(lessonId));
			}else if (!parameters.get("email").isNull() && students.read(parameters.get("email").toString()) != null){
				students.lessonCancel(students.read(parameters.get("email").toString()), lessons.read(lessonId));
			}
		}
        
		if(!parameters.get("my").isNull() && !parameters.get("email").isNull() && students.read(parameters.get("email").toString()) != null){
			lsns = students.getAllLessons(students.read(parameters.get("email").toString()));
            my = true;
		}else{
			lsns = lessons.getUpcomingLessons(new Date((new java.util.Date()).getTime()+86400000*21));
		}
        
        
		LessonList lessonList = new LessonList("lekce", lsns, my);
		add(lessonList);
    }
}
