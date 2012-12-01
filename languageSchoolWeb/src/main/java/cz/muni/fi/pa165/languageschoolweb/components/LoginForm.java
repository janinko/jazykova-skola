package cz.muni.fi.pa165.languageschoolweb.components;

import cz.muni.fi.pa165.languageschool.api.adapters.StudentDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.adapters.TeacherDtoAdapter;
import cz.muni.fi.pa165.languageschool.api.dto.StudentDto;
import cz.muni.fi.pa165.languageschool.api.dto.TeacherDto;
import cz.muni.fi.pa165.languageschoolweb.HomePage;
import cz.muni.fi.pa165.languageschoolweb.model.LoginModel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author xbrazdi1, xchrastk, xkelnar
 */
public class LoginForm extends Panel{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private StudentDtoAdapter students;
	@SpringBean
	private TeacherDtoAdapter teachers;

	public LoginForm(String id){
		super(id);
		add(new InputForm("generateForm"));
	}

	class InputForm extends Form<LoginModel>{
		private static final long serialVersionUID = 1L;

        public InputForm(String id){
			super(id, new CompoundPropertyModel<LoginModel>(new LoginModel()));

            add(new TextField<String>("email", String.class).setRequired(true));
            add(new TextField<String>("password", String.class).setRequired(true));

            add(new Button("loginButton"));
		}

		@Override
		protected void onError() {			
			PageParameters params = new PageParameters();
			getSession().error("Chyba v zadavani modelu");
			setResponsePage(HomePage.class,params);
		}

		@Override
        public void onSubmit()
        {
			setResponsePage(HomePage.class);
			
			LoginModel model = (LoginModel) getDefaultModelObject();
			if(model.getEmail() == null || model.getEmail().isEmpty()){
				getSession().error("Musíš zadat email");
				return;
			}
			if(model.getPassword() == null || model.getPassword().isEmpty()){
				getSession().error("Musíš zadat heslo");
				return;
			}

			StudentDto student = students.authentize(model.getEmail(), model.getPassword());
			if(student != null){
				getSession().info("Přihlášen jako student: " + student.getFirstName() + student.getLastName());
				return;
			}

			TeacherDto teacher = teachers.authentize(model.getEmail(), model.getPassword());
			if (teacher != null) {
				getSession().info("Přihlášen jako učitel: " + teacher.getFirstName() + teacher.getLastName());
				return;
			}

			getSession().error("Špatné email nebo heslo");
        }
	}
}
