/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.LessonDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.LessonDto;
import cz.muni.fi.pa165.languageschoolweb.components.LessonList;
import java.util.Set;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class LessonsPage extends BasePage {
    @SpringBean
    private LessonDtoAdapter lessons;
    
    public LessonsPage() {

		LessonList lessonsList = new LessonList("lessonList",lessons.getAllLessons());

		add(lessonsList);

    }
}
