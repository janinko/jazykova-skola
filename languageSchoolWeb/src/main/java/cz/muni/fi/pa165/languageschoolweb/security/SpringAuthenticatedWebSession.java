package cz.muni.fi.pa165.languageschoolweb.security;

import cz.muni.fi.pa165.languageschool.services.LanguageschoolUserDetailsService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class SpringAuthenticatedWebSession extends AuthenticatedWebSession {
	private static final long serialVersionUID = 1L;

	@SpringBean(name="authenticationManager")
	private AuthenticationManager authenticationManager;

	public SpringAuthenticatedWebSession(Request request) {
        super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		if (authenticationManager == null) {
			System.out.println("je to nuuuuul");
		}
		
		boolean authenticated = false;
		try{
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			authenticated = authentication.isAuthenticated();
		}catch(AuthenticationException ex){
			Logger.getLogger(SpringAuthenticatedWebSession.class.getName()).log(Level.WARNING, "Failed to generate data", ex);
			authenticated = false;
		}
		return authenticated;
	}

	@Override
	public Roles getRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Roles roles = new Roles();
		if(authentication != null){
			this.signIn(true);
			for(GrantedAuthority authority : authentication.getAuthorities()){
				roles.add(authority.getAuthority());
			}
		}
		return roles;
	}

	public void logout(){
		this.invalidate();
		if(SecurityContextHolder.getContext() != null){
			SecurityContextHolder.getContext().setAuthentication(null);
			this.signOut();
		}
	}
}
