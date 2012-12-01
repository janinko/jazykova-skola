package cz.muni.fi.pa165.languageschoolweb;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
    
    public HomePage(PageParameters parameters) {
		super(parameters);

		PageParameters params = new PageParameters();
		params.set("generate","");
		add(new BookmarkablePageLink<ActionPage>("generatorLink",ActionPage.class, params));
    }
}
