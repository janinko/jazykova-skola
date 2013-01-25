package cz.muni.fi.pa165.languageschoolweb.model;

import org.apache.wicket.util.io.IClusterable;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class RegistrationModel implements IClusterable {
	private static final long serialVersionUID = 1L;

	String email;
	String firstname;
	String lastname;
	String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
