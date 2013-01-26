package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschoolweb.security.SpringAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class LogoutPage extends BasePage {
	private static final long serialVersionUID = 1L;

    public LogoutPage(PageParameters parameters) {
		super(parameters);

		SpringAuthenticatedWebSession session = (SpringAuthenticatedWebSession) AuthenticatedWebSession.get();
		
		session.logout();
    }
}
