package cz.muni.fi.pa165.languageschoolweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
 
public class ErrorPage extends WebPage {
 
	public ErrorPage(final PageParameters parameters) { 
	}

	@Override
	public boolean isVersioned() {
		return false;
	}

	@Override
	public boolean isErrorPage() {
		return true;
	}
}