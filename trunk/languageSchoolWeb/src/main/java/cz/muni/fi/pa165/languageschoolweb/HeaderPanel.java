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
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 1L;
	@SpringBean
	private StudentDtoAdapter students;
    @SpringBean
	private TeacherDtoAdapter teachers;

	public HeaderPanel(String componentName) {
		super(componentName);
        
    	StudentDto student = null;//students.read(1);
        //TeacherDto student = teachers.readTeacher(1);
        PageParameters accountParams = new PageParameters();
		PageParameters lessonsParams = new PageParameters();
        
       	lessonsParams.set("my", true);
        
		Link<LessonsPage> lessonsLink = new BookmarkablePageLink<LessonsPage>("myLessons", LessonsPage.class, lessonsParams);
        Link accountLink = new BookmarkablePageLink<AccountPage>("myAccount", AccountPage.class, accountParams);
        Link<HomePage> logoutLink = new BookmarkablePageLink<HomePage>("newAccount", RegistrationPage.class);

		if(student == null){
			accountLink = new BookmarkablePageLink<LoginPage>("myAccount", LoginPage.class);
            accountLink.add(new Label("label", new ResourceModel("Prihlasit")));
            lessonsLink.setVisible(false);
			logoutLink.add(new Label("label", new ResourceModel("register")));
		} else {
            accountParams.set("email", student.getEmail().toString());
            accountLink = new BookmarkablePageLink<AccountPage>("myAccount", AccountPage.class, accountParams);
            accountLink.add(new Label("label", student.getFirstName() + " " + student.getLastName()));
			logoutLink.add(new Label("label", new ResourceModel("logout")));
		}
        
        add(accountLink);
        add(lessonsLink);
        add(logoutLink);
	}
}
