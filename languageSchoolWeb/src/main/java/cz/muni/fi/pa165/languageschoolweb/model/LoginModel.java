package cz.muni.fi.pa165.languageschoolweb.model;

import org.apache.wicket.util.io.IClusterable;

/**
 * @author
 */
public class LoginModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	String email;
	String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
