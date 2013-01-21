package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschoolweb.components.LoginForm;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class LoginPage extends BasePage {
	private static final long serialVersionUID = 1L;

    public LoginPage(PageParameters parameters) {
		super(parameters);

		add(new LoginForm("loginPanel"));
    }
}
