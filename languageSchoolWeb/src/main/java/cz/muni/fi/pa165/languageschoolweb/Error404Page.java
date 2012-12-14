package cz.muni.fi.pa165.languageschoolweb;

import org.apache.wicket.request.mapper.parameter.PageParameters;
 
public class Error404Page extends BasePage {
 
	public Error404Page(PageParameters parameters) {
		super(parameters);
		setStatelessHint(true);
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