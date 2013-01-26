package cz.muni.fi.pa165.languageschoolweb;           

import cz.muni.fi.pa165.languageschoolweb.security.UserRolesAuthorizer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/** 
 * @author xbrazdi1, xchrastk, xkelnar
 */
public abstract class BasePage extends WebPage {
	
	
	UserRolesAuthorizer ura = new UserRolesAuthorizer();

    public BasePage(PageParameters parameters) {
        super(parameters);

        add(new HeaderPanel("headerpanel")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket"));

		add(new FeedbackPanel("feedback"));
		add(new BookmarkablePageLink<CoursesPage>("courses", CoursesPage.class));

		add(new BookmarkablePageLink<LessonsPage>("lessons", LessonsPage.class));
		

		add(new BookmarkablePageLink<TeachersPage>("teachers", TeachersPage.class));//.setVisible(ura.hasAnyRole(new Roles(Roles.ADMIN))));
		
    } 
}
