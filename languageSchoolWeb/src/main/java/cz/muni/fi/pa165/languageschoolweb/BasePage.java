/*
 * WicketExamplePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
 
package cz.muni.fi.pa165.languageschoolweb;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/** 
 *
 * @author kelnar
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage(PageParameters parameters) {
        super(parameters);

		Label message = new Label("message");
		message.setVisible(false);
		Label errorMessage = new Label("errorMessage");
		message.setVisible(false);
		if(parameters != null){
			if(!parameters.get("error").isNull()){
				errorMessage = new Label("errorMessage",parameters.get("error").toString());
			}
			if(!parameters.get("message").isNull()){
				message = new Label("message",parameters.get("message").toString());
			}
		}

		add(message);
		add(errorMessage);
        add(new HeaderPanel("headerpanel")); 
        add(new FooterPanel("footerpanel", "Powered by Wicket"));

		add(new BookmarkablePageLink("courses", CoursesPage.class));

		add(new BookmarkablePageLink("lessons", LessonsPage.class));
		add(new BookmarkablePageLink("teachers", TeachersPage.class));
    } 

}
