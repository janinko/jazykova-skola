/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import java.util.Set;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class LessonsPage extends BasePage {
    @SpringBean
    private LessonDtoAdapter lessons;
    @SpringBean
    private StudentDtoAdapter students;
    @SpringBean
    private GenerateDataService generator;

    public LessonsPage(PageParameters parameters) {
		Set<LessonDto> lsns;
		if(!parameters.get("my").isNull() && students.read(1) != null){
			lsns = students.getAllLessons(students.read(1));
		}else{
			lsns = lessons.getAllLessons();
		}

		LessonList lessonList = new LessonList("lekce", lsns);
		add(lessonList);
    }
}
