package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import cz.muni.fi.pa165.languageschoolweb.security.UserRolesAuthorizer;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketApplication extends AuthenticatedWebApplication
{    	
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	public void init()
	{
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		mountPage("/course", CoursePage.class);
		mountPage("/courses", CoursesPage.class);
		mountPage("/lessons", LessonsPage.class);
		mountPage("/teacher", TeacherPage.class);
		mountPage("/teachers", TeachersPage.class);
        mountPage("/account", AccountPage.class);
        mountPage("/action", ActionPage.class);
        mountPage("/error", ErrorPage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/404", Error404Page.class);
		
		getSecuritySettings().setAuthorizationStrategy(new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SpringAuthenticatedWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new SpringAuthenticatedWebSession(request);
	}
}
