/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.CourseDtoAdapter;
import cz.muni.fi.pa165.languageschoolweb.components.AddCourseForm;
import cz.muni.fi.pa165.languageschoolweb.components.CourseList;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class CoursesPage extends BasePage {
    @SpringBean
    private CourseDtoAdapter courses;
    
    public CoursesPage(PageParameters parameters) {
		super(parameters);

		Long courseid = parameters.get("courseid").toLong(-1);
		String act = parameters.get("act").toString();

		if ( (act != null) && act.equals("delete") && (courseid != null) ) {
			courses.deleteCourse(courses.read(courseid));
			setResponsePage(CoursesPage.class);
		}
		
		CourseList courseList = new CourseList("courseList",courses.getAllCourses());
		
		add(courseList);
		add(new AddCourseForm("addCourseForm"));
    }
}
