package cz.muni.fi.pa165.languageschoolweb;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschoolweb.components.ChangeStudent;
import cz.muni.fi.pa165.languageschoolweb.components.ChangeTeacher;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class AccountPage extends BasePage {
	private static final long serialVersionUID = 1L;
    @SpringBean
    private StudentDtoAdapter students;
    @SpringBean
    private TeacherDtoAdapter teachers;
    
    public AccountPage(PageParameters parameters) {
        super(parameters);             
        
        String email = parameters.get("email").toString();
        
        
        if(teachers.readTeacher(email) != null) {
            ChangeTeacher changeTeacher = new ChangeTeacher("changeAccount",email);
            add(changeTeacher);
        } else {
            ChangeStudent changeStudent = new ChangeStudent("changeAccount",email);
            add(changeStudent);
        }
    }
}
