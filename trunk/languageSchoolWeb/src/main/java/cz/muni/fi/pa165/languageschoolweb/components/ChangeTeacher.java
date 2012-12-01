package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschool.api.entities.Teacher;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.LanguagesModel;
import cz.muni.fi.pa165.languageschoolweb.model.TeacherModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author
 */
public class ChangeTeacher extends Panel{
	private static final long serialVersionUID = 1L;

    @SpringBean
    private TeacherDtoAdapter teachers;

    private TeacherDto teacher;
    private List<String> languages = new ArrayList<String>();
    
	public ChangeTeacher(String id, final String email){
        super(id);

        teacher = teachers.readTeacher(email);
		for(Teacher.Language l : Teacher.Language.values()){
			languages.add(l.toString());
		}
        final TeacherDto model = new TeacherDto();
        
        /*if(email == null){
			add(new Label("error", "V URL nenalezen email teachera."));
			return;
		} else if(teachers.read(email) == null){
			add(new Label("error", "Teacher s emailem" + email +"neexistuje!"));
			return;
		} else {
            
        }*/
        
        Label name = new Label("name", "Teacher " + teacher.getFirstName() + " " +teacher.getLastName());
        
        Form form = new InputForm("form", teacher);

        add(form);
        add(name);
	}


	class InputForm extends Form<TeacherModel>{
		private static final long serialVersionUID = 1L;

        public InputForm(String id, TeacherDto teacher){
			super(id, new CompoundPropertyModel<TeacherModel>(new TeacherModel(teacher)));

            add(new TextField<String>("firstName", String.class).setRequired(true));
            add(new TextField<String>("lastName", String.class).setRequired(true));
            add(new ListMultipleChoice<String>("languages", languages));
            add(new TextField<String>("newPassword", String.class));
            add(new TextField<String>("newPasswordRepeat", String.class));
			add(new Button("save"));
		}

		@Override
        public void onSubmit()
        {
			TeacherModel model = (TeacherModel) getDefaultModelObject();
			
			if(!validate(model)){
			setResponsePage(getPage());
				return;
			}

			TeacherDto teacher = model.getTeacher();
			if(teacher == null) teacher = new TeacherDto();

			teacher.setFirstName(model.getFirstName());
			teacher.setLastName(model.getLastName());
            teacher.setLanguages(model.getLanguages());
			//teacher.setEmail(model.getEmail()); // TODO

			if(model.getTeacher() == null){ // creating new teacher
				try{
					teachers.createTeacher(teacher);
					teachers.setPassword(teacher, model.getNewPassword());
					getSession().info(getString("teacherCreated"));
				}catch(Exception ex){
					getSession().error(getString("teacherNotCreated"));
					getSession().error(ex);
				}
			}else{ // updating teacher
				try{
					teachers.updateTeacher(teacher);
					if(model.getNewPassword() != null){
						teachers.setPassword(teacher, model.getNewPassword());
					getSession().info(getString("passChanged"));
					}
					getSession().info(getString("teacherChanged"));
				}catch(Exception ex){
					getSession().error(getString("teacherNotChanged"));
					getSession().error(ex);
				}
			}

			setResponsePage(HomePage.class);
        }

		private boolean validate(TeacherModel model){
			boolean ok=true;
			if(model.getNewPassword() != null && model.getNewPassword().isEmpty() ){
				getSession().error(getString("wEmptyPassword"));
				ok = false;
			}
			if(model.getNewPassword() != null && !model.getNewPassword().equals(model.getNewPasswordRepeat())){
				getSession().error(getString("wDifferentPassword"));
				ok = false;
			}
			if(model.getTeacher() == null && model.getNewPassword() == null){
				getSession().error(getString("wNoPassword"));
				ok = false;
			}
			return ok;
		}
	}
}