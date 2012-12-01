/*
 * HeaderPanel.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author kelnar
 * @version
 */
public class HeaderPanel extends Panel {

	@SpringBean
	private StudentDtoAdapter students;
    @SpringBean
	private TeacherDtoAdapter teachers;

	/**
	 * Construct.
	 *
	 * @param componentName name of the component
	 * @param exampleTitle title of the example
	 */
	public HeaderPanel(String componentName) {
		super(componentName);
        
    	StudentDto student = students.read(1);
        PageParameters accountParams = new PageParameters();
		PageParameters lessonsParams = new PageParameters();
        
       	lessonsParams.set("my", true);
        
		Link lessonsLink = new BookmarkablePageLink("myLessons", LessonsPage.class, lessonsParams);		
        Link accountLink = new BookmarkablePageLink("myAccount", HomePage.class, accountParams);

		if(student == null){			
            accountLink.add(new Label("username", new ResourceModel("Prihlasit")));			
            lessonsLink.setVisible(false);
		} else {      
            accountParams.set("email", student.getEmail().toString());
            accountLink = new BookmarkablePageLink("myAccount", AccountPage.class, accountParams);
            accountLink.add(new Label("username", student.getFirstName() + " " + student.getLastName() + " [editovat]"));                  
		}
        
        add(accountLink);
        add(lessonsLink);
	}
}
