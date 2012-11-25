/*
 * WicketExamplePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
 
package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/** 
 *
 * @author kelnar
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket"));

		add(new BookmarkablePageLink("courses", CoursesPage.class));

		add(new BookmarkablePageLink("lessons", LessonsPage.class));
		add(new BookmarkablePageLink("teachers", TeachersPage.class));
    } 

}
