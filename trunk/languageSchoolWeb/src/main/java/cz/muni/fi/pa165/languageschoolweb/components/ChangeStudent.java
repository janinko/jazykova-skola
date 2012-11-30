package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschoolweb.AccountPage;
import java.io.Serializable;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author xchrastk
 */
public class ChangeStudent extends Panel{
	private static final long serialVersionUID = 1L;

    @SpringBean
    private StudentDtoAdapter students;

    private StudentDto student;
    
	public ChangeStudent(String id, final String email){
        super(id);

        student = students.read(email);
        final StudentDto model = new StudentDto();
        
        /*if(email == null){
			add(new Label("error", "V URL nenalezen email studenta."));
			return;
		} else if(students.read(email) == null){
			add(new Label("error", "Student s emailem" + email +"neexistuje!"));
			return;
		} else {
            
        }*/
        
        Label name = new Label("name", "Student " + student.getFirstName() + " " +student.getLastName());
        
        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };
        
        final TextField<String> nameField = new TextField<String>("nameField",new PropertyModel(model,"firstName"));
        final TextField<String> surnameField = new TextField<String>("surnameField",new PropertyModel(model,"lastName"));
        final TextField<String> passwordField = new TextField<String>("passwordField",Model.of(""));
        
        Button button = new Button("save") {
            public void onSubmit() {
                boolean changed = false;
                if (model.getFirstName() != null && !student.getFirstName().equals(model.getFirstName())) {                    
                    student.setFirstName(model.getFirstName()); 
                    changed = true;
                }   
                if (model.getLastName() != null && !student.getLastName().equals(model.getLastName())) {                    
                    student.setLastName(model.getLastName());
                    changed = true;
                    
                }     
                
                if (changed) {students.update(student);}
                PageParameters params = new PageParameters();
                params.set("email", student.getEmail());
                throw new RestartResponseException(AccountPage.class, params);

            }
        };


        form.add(button);
        form.add(nameField);
        form.add(surnameField);
        form.add(passwordField);
        add(form);
        add(name);
	}

	
	class CheckBoxModel implements Serializable {

		private static final long serialVersionUID = 1L;
		private boolean checked;

		CheckBoxModel(Boolean checked) {
			this.checked = checked;
		}

		boolean isChecked() {
			return checked;
		}
	}
}