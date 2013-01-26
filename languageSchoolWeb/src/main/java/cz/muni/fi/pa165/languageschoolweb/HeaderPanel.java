package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.api.services.TeacherService;
import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import cz.muni.fi.pa165.languageschoolweb.security.UserRolesAuthorizer;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
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
	
	UserRolesAuthorizer ura = new UserRolesAuthorizer();
	

	public HeaderPanel(String componentName) {
		super(componentName);

		SpringAuthenticatedWebSession session = (SpringAuthenticatedWebSession) AuthenticatedWebSession.get();
        
        PageParameters accountParams = new PageParameters();
		PageParameters lessonsParams = new PageParameters();
        
       	lessonsParams.set("my", true);
        
		Link<LessonsPage> lessonsLink = new BookmarkablePageLink<LessonsPage>("myLessons", LessonsPage.class, lessonsParams);
        Link accountLink; //= new BookmarkablePageLink<AccountPage>("myAccount", AccountPage.class, accountParams);
        Link<HomePage> logoutLink;
        Link registrationLink = new BookmarkablePageLink<HomePage>("registration", RegistrationPage.class);

		if(session.getLogged() == null){
            accountLink = new BookmarkablePageLink<LoginPage>("myAccount", LoginPage.class);
            accountLink.add(new Label("label", new ResourceModel("Prihlasit")));
            lessonsLink.setVisible(false);
            logoutLink = new BookmarkablePageLink<HomePage>("newAccount", RegistrationPage.class);
            logoutLink.setVisible(false);
			registrationLink.setVisible(false);
		} else {
			String email = session.getLogged();
			
			System.out.println("------- registrovany: " + email);

            accountParams.set("email", email);
            accountLink = new BookmarkablePageLink<AccountPage>("myAccount", AccountPage.class, accountParams);

			StudentDto s = students.read(email);
			if(s != null){
				accountLink.add(new Label("label", s.getFirstName() + " " + s.getLastName()));
			}else {
				TeacherDto t = teachers.readTeacher(email);
				
				if (t != null) {
					accountLink.add(new Label("label", t.getFirstName() + " " + t.getLastName() ));
				} else {
					accountLink.add(new Label("label", "Root Admin"));
				}
			}

			logoutLink = new BookmarkablePageLink<HomePage>("newAccount", LogoutPage.class);
			logoutLink.add(new Label("label", new ResourceModel("logout")));
            
			registrationLink.add(new Label("label", new ResourceModel("register"))).setVisible(ura.hasAnyRole(new Roles("ROLE_ADMIN")));            
		
		}
        
        add(accountLink);        
        add(registrationLink);
        add(lessonsLink.setVisible(ura.hasAnyRole(new Roles(Roles.USER))));
        add(logoutLink);
	}
}
