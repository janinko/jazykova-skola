package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.Language;
import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.RegistrationModel;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class RegistrationForm extends Panel{
	private static final long serialVersionUID = 1L;

	/*@SpringBean
	private StudentDtoAdapter students;
	@SpringBean
	private TeacherDtoAdapter teachers;*/
	
	
	@SpringBean
    private StudentDtoAdapter students;
	
	@SpringBean
    private TeacherDtoAdapter teachers;

	

	public RegistrationForm(String id){
		super(id);
		add(new InputRegForm("generateForm"));
	}

	class InputRegForm extends Form<RegistrationModel>{
		private static final long serialVersionUID = 1L;

		private String selected = "student";

		public String getSelected() {
			return selected;
		}

		public void setSelected(String selected) {
			this.selected = selected;
		}

        public InputRegForm(String id){
			super(id, new CompoundPropertyModel<RegistrationModel>(new RegistrationModel()));

			Select s = new Select("usertypes", new PropertyModel<String>(this, "selected"));
			s.add(new SelectOption<String>("teachertype",new Model<String>("teacher")));
			s.add(new SelectOption<String>("studenttype",new Model<String>("student")));
			add(s);
			//add(new TextField<String>("usertype", String.class).setRequired(true)); // "student" | "teacher"
            add(new TextField<String>("email", String.class).setRequired(true));
			add(new TextField<String>("firstname", String.class).setRequired(true));
			add(new TextField<String>("lastname", String.class).setRequired(true));
            add(new PasswordTextField("password").setRequired(true));

            add(new Button("loginButton"));
		}

		@Override
		protected void onError() {
			getSession().error("Chyba v zadavani modelu");
			//setResponsePage(HomePage.class);
		}

		@Override
        public void onSubmit()
        {
			
			RegistrationModel model = (RegistrationModel) getDefaultModelObject();

			AuthenticatedWebSession session = AuthenticatedWebSession.get();

			// STUDENT
			if ("student".equals(selected)) {
				
				// email still is not used
				if (students.read(model.getEmail()) == null) {
					StudentDto student = new StudentDto();

					student.setFirstName(model.getFirstname());
					student.setLastName(model.getLastname());
					student.setAge(0);
					student.setEmail(model.getEmail());

					try{
						students.createStudent(student);
						session.authenticate(model.getEmail(), "pass");
						students.setPassword(student, model.getPassword());
						getSession().info(getString("studentCreated"));
					}catch(Exception ex){
						getSession().error(getString("studentNotCreated"));
						getSession().error(ex);
						Logger.getLogger(RegistrationForm.class.getName()).log(Level.WARNING, "Failed to create student", ex);
					}

					setResponsePage(HomePage.class);
				} 
			
				
			// TEACHER
			} else if ("teacher".equals(selected) || "ucitel".equals(selected)) {
				
				// email still is not used
				if (teachers.readTeacher(model.getEmail()) == null) {

					TeacherDto teacher = new TeacherDto();

					teacher.setFirstName(model.getFirstname());
					teacher.setLastName(model.getLastname());
					
					// default language is AJ, it can be changed in teacher page
					HashSet<Language> defaultLanguage = new HashSet<Language>();
					defaultLanguage.add(Language.AJ);
					teacher.setLanguages(defaultLanguage);
					teacher.setNativeLanguage(Language.AJ);
					
					teacher.setEmail(model.getEmail());

					try{
						teachers.createTeacher(teacher);
						session.authenticate(model.getEmail(), "pass");
						teachers.setPassword(teacher, model.getPassword());
						getSession().info(getString("teacherCreated"));
					}catch(Exception ex){
						getSession().error(getString("teacherNotCreated"));
						getSession().error(ex);
						Logger.getLogger(RegistrationForm.class.getName()).log(Level.WARNING, "Failed to create teacher", ex);
					}

					setResponsePage(HomePage.class);
				} 
			}
			
        }
	}
}
