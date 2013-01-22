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

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * Web Session for this example.
 * 
 * @author kelnar
 */
public class RolesSession extends WebSession
{
    /** the current user. */
    private User user = new User("petr@novak.cz", Roles.ADMIN);

    /**
     * Construct.
     * 
     * @param request
     */
    public RolesSession(Request request)
    {
        super(request);
    }

    /**
     * Gets user.
     * 
     * @return user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Sets user.
     * 
     * @param user
     *            user
     */
    public void setUser(User user)
    {
        this.user = user;
    }

}
