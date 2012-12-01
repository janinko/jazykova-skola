/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
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
			lessons.removeLesson(lessons.read(lessonId));
			//setResponsePage(LessonsPage.class);
		}
        
		if(!parameters.get("my").isNull() && students.read(1) != null){
			lsns = students.getAllLessons(students.read(1));
            my = true;
		}else{
			lsns = lessons.getAllLessons();
		}
        
        
		LessonList lessonList = new LessonList("lekce", lsns, my);
		add(lessonList);
    }
}
