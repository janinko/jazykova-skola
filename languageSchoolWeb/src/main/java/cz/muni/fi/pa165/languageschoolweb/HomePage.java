/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.services.GenerateDataService;
import cz.muni.fi.pa165.languageschoolweb.components.GeneratorForm;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private GenerateDataService generator;

	private static boolean generated = false;
    
    public HomePage(PageParameters parameters) {
		super(parameters);

		PageParameters params = new PageParameters();
		params.set("generate","");
		add(new BookmarkablePageLink("generatorLink",ActionPage.class, params));
    }
}
