package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import java.io.Serializable;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
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
    
	public ChangeStudent(String id, String email){
        super(id);

        student = students.read(email);
        
        /*if(email == null){
			add(new Label("error", "V URL nenalezen email studenta."));
			return;
		} else if(students.read(email) == null){
			add(new Label("error", "Student s emailem" + email +"neexistuje!"));
			return;
		} else {
            
        }*/
        
        Label name;
        if(student != null) name = new Label("name", "Student " + student.getFirstName() + " " +student.getLastName());
        else name = new Label("name", "nyc");
        
        Form form = new Form("form") {
            protected void onSubmit() {
                info("Form.onSubmit executed");
            }
        };
        
        final TextField<String> nameField = new TextField<String>("nameField",Model.of(""));
        final TextField<String> surnameField = new TextField<String>("surnameField",Model.of(""));
        final TextField<String> passwordField = new TextField<String>("passwordField",Model.of(""));
        
        Button button = new Button("save") {
            public void onSubmit() {
                
                throw new RestartResponseException(this.getPage());

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