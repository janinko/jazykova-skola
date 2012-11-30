/*
 * HomePage.java
 *
 * Created on 21. listopad 2012, 20:21
 */
package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschoolweb.components.ChangeStudent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
//TODO lokalizace

public class AccountPage extends BasePage {
    @SpringBean
    private StudentDtoAdapter students;
    
    public AccountPage(PageParameters parameters) {
        super(parameters);             
        
		ChangeStudent changeStudent = new ChangeStudent("changeStudent",parameters.get("email").toString());

		add(changeStudent);
    }
}
