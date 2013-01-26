package cz.muni.fi.pa165.languageschoolweb.security;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
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

	private String logged;
    private Roles loggedRoles = new Roles();

	@SpringBean(name="authenticationManager")
	private AuthenticationManager authenticationManager;

	public SpringAuthenticatedWebSession(Request request) {
        super(request);
		Injector.get().inject(this);
	}

	@Override
	public boolean authenticate(String username, String password) {		
		boolean authenticated;
		try{
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			authenticated = authentication.isAuthenticated();
			logged = username;
		}catch(AuthenticationException ex){
			Logger.getLogger(SpringAuthenticatedWebSession.class.getName()).log(Level.WARNING, "Failed to generate data", ex);
			authenticated = false;
		}
        System.out.println(authenticated);
        return authenticated;
	}

	@Override
	public Roles getRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Roles roles = new Roles();
		if(authentication != null){
			this.signIn(true);
			for(GrantedAuthority authority : authentication.getAuthorities()){
				roles.add(authority.toString());
                System.out.println("auth: "+authority.getAuthority());
			}
            if(!roles.isEmpty()) {
            System.out.println("roles are not empty");
        
    } else {
                System.out.println("roles are empty");
            }
		} else {System.out.println("not authenticated");}
        
		return roles;
	}

	public void logout(){
		if (loggedRoles!=null) { loggedRoles.clear(); }
        this.invalidate();
		if(SecurityContextHolder.getContext() != null){
			SecurityContextHolder.getContext().setAuthentication(null);
			this.signOut();
		}
	}

	public String getLogged() {
		return logged;
	}
    
    public Roles getLoggedRoles() {
        return loggedRoles;
    }
    
    public void fill() {
        if(loggedRoles.isEmpty()) {loggedRoles.addAll(getRoles());
        }
    }
}
