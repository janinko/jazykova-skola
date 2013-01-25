/*
 * Copyright 2013 kelnar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.muni.fi.pa165.languageschoolweb.security;

import org.apache.wicket.Session;
import java.io.Serializable;
import javax.swing.Spring;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;


/**
 * The authorizer we need to provide to the authorization strategy implementation
 * {@link org.apache.wicket.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy}
 * .
 * 
 * @author kelnar
 */
public class UserRolesAuthorizer implements IRoleCheckingStrategy,Serializable
{
	private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public UserRolesAuthorizer()
    {
    }

    /**
     * @see org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy#hasAnyRole(Roles)
     */
    @Override
    public boolean hasAnyRole(Roles roles)
    {
        //AuthenticatedWebSession session = AuthenticatedWebSession.get();
		SpringAuthenticatedWebSession session = (SpringAuthenticatedWebSession) AuthenticatedWebSession.get();
		
		if ( (session.getLogged() != null) && ("admin@admin.com".equals(session.getLogged()))) {
			return true;
		} else {
			Roles authRoles = session.getRoles();
			return authRoles.hasAnyRole(roles);
		}
    }
}