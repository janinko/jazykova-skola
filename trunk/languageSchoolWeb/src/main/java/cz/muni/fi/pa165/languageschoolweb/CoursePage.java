/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.CourseDto;
import cz.muni.fi.pa165.languageschoolweb.components.CourseList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class CoursePage extends BasePage {
    @SpringBean
    private CourseDtoAdapter courses;
    
    public CoursePage(PageParameters parameters) {
		Long courseid = parameters.get("courseid").toLong(-1);

		if(courseid == -1){
			add(new Label("courseName", "Musíš vybrat kurz."));
			return;
		}

		CourseDto course = null;
		for(CourseDto c : courses.getAllCourses()){
			if(c.getId() == courseid){
				course = c;
				break;
			}
		}
		
		if(course == null){
			add(new Label("courseName", "Kurz neexistuje!"));
			return;
		}

		add(new Label("courseName", course.getName()));
    }
}
