package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.StudentModel;
import java.io.Serializable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
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
        
        Form form = new InputForm("form", student);

        add(form);
        add(name);
	}


	class InputForm extends Form<StudentModel>{
		private static final long serialVersionUID = 1L;

        public InputForm(String id, StudentDto student){
			super(id, new CompoundPropertyModel<StudentModel>(new StudentModel(student)));

            add(new TextField<String>("firstName", String.class).setRequired(true));
            add(new TextField<String>("lastName", String.class).setRequired(true));
            add(new TextField<Integer>("age", Integer.class).setRequired(true));
            add(new TextField<String>("newPassword", String.class));
            add(new TextField<String>("newPasswordRepeat", String.class));
			add(new Button("save"));
		}

		@Override
        public void onSubmit()
        {
			StudentModel model = (StudentModel) getDefaultModelObject();
			
			if(!validate(model)){
			setResponsePage(getPage());
				return;
			}

			StudentDto student = model.getStudent();
			if(student == null) student = new StudentDto();

			student.setFirstName(model.getFirstName());
			student.setLastName(model.getLastName());
			student.setAge(model.getAge());
			//student.setEmail(model.getEmail()); // TODO

			if(model.getStudent() == null){ // creating new student
				try{
					students.createStudent(student);
					students.setPassword(student, model.getNewPassword());
					getSession().info(getString("studentCreated"));
				}catch(Exception ex){
					getSession().error(getString("studentNotCreated"));
					getSession().error(ex);
				}
			}else{ // updating student
				try{
					students.update(student);
					if(model.getNewPassword() != null){
						students.setPassword(student, model.getNewPassword());
					getSession().info(getString("passChanged"));
					}
					getSession().info(getString("studentChanged"));
				}catch(Exception ex){
					getSession().error(getString("studentNotChanged"));
					getSession().error(ex);
				}
			}

			setResponsePage(HomePage.class);
        }

		private boolean validate(StudentModel model){
			boolean ok=true;
			if(model.getNewPassword() != null && model.getNewPassword().isEmpty() ){
				getSession().error(getString("wEmptyPassword"));
				ok = false;
			}
			if(model.getNewPassword() != null && !model.getNewPassword().equals(model.getNewPasswordRepeat())){
				getSession().error(getString("wDifferentPassword"));
				ok = false;
			}
			if(model.getStudent() == null && model.getNewPassword() == null){
				getSession().error(getString("wNoPassword"));
				ok = false;
			}
			return ok;
		}
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