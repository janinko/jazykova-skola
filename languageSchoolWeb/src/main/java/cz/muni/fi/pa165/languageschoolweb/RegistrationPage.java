package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschoolweb.components.RegistrationForm;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@AuthorizeAction(action = "RENDER", roles = { Roles.ADMIN })
public class RegistrationPage extends BasePage {
	private static final long serialVersionUID = 1L;

    public RegistrationPage(PageParameters parameters) {
		super(parameters);

		add(new RegistrationForm("registrationPanel"));
    }
}
