package cz.muni.fi.pa165.languageschoolweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketApplication extends WebApplication
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
        mountPage("/404", Error404Page.class);
	}
}
